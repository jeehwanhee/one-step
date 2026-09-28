package com.jeepark.onestep.di

import android.app.Application
import android.content.Context
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.firestore
import com.jeepark.onestep.BuildConfig
import com.jeepark.onestep.data.local.SharedPrefsActiveQuestStore
import com.jeepark.onestep.data.local.SharedPrefsSettingsRepository
import com.jeepark.onestep.data.local.SharedPrefsVersionStore
import com.jeepark.onestep.data.remote.FirestoreCollectionSource
import com.jeepark.onestep.data.remote.GeminiClient
import com.jeepark.onestep.data.remote.NetworkClient
import com.jeepark.onestep.data.repository.AccountService
import com.jeepark.onestep.data.repository.ActiveQuestStore
import com.jeepark.onestep.data.repository.AuthRepository
import com.jeepark.onestep.data.repository.FirebaseAuthRepository
import com.jeepark.onestep.data.repository.GeminiQuestRanker
import com.jeepark.onestep.data.repository.KmaWeatherProvider
import com.jeepark.onestep.data.repository.PlaceRepositoryImpl
import com.jeepark.onestep.data.repository.QuestRepository
import com.jeepark.onestep.data.repository.QuestRepositoryImpl
import com.jeepark.onestep.data.repository.SettingsRepository
import com.jeepark.onestep.data.repository.UserRepository
import com.jeepark.onestep.data.repository.UserRepositoryImpl
import com.jeepark.onestep.data.repository.VersionedCache
import com.jeepark.onestep.domain.model.FirestorePaths
import com.jeepark.onestep.domain.model.Place
import com.jeepark.onestep.domain.model.Quest
import com.jeepark.onestep.platform.location.FusedLocationProvider
import com.jeepark.onestep.platform.location.LocationProvider
import com.jeepark.onestep.platform.notification.NotificationScheduler
import com.jeepark.onestep.platform.notification.WorkManagerNotificationScheduler

/**
 * 앱 전체가 함께 쓰는 의존성을 한 곳에서 만들고 보관한다(수동 DI, 별도 라이브러리 없음).
 * 각 항목은 처음 꺼낼 때 한 번만 만들어져서, 쓰지 않는 저장소는 Firebase에 접근하지 않는다.
 *
 * 생성 함수를 주입받으므로 테스트에서는 가짜 저장소로 채울 수 있다. 실제 구현 연결은 [create].
 * 새 저장소·서비스가 생기면 여기에 추가하고, ViewModel 팩토리는 이 컨테이너에서 꺼내 쓴다.
 */
class AppContainer(
    createAuthRepository: () -> AuthRepository,
    createUserRepository: () -> UserRepository,
    createQuestRepository: () -> QuestRepository,
    createActiveQuestStore: () -> ActiveQuestStore,
    createSettingsRepository: () -> SettingsRepository,
    createNotificationScheduler: () -> NotificationScheduler,
    createLocationProvider: () -> LocationProvider,
) {
    val authRepository: AuthRepository by lazy(createAuthRepository)
    val userRepository: UserRepository by lazy(createUserRepository)
    val questRepository: QuestRepository by lazy(createQuestRepository)
    val activeQuestStore: ActiveQuestStore by lazy(createActiveQuestStore)
    val settingsRepository: SettingsRepository by lazy(createSettingsRepository)
    val notificationScheduler: NotificationScheduler by lazy(createNotificationScheduler)
    val locationProvider: LocationProvider by lazy(createLocationProvider)

    /** 로그아웃·계정 삭제. 위의 저장소들을 조합한다. */
    val accountService: AccountService by lazy {
        AccountService(authRepository, userRepository, settingsRepository, notificationScheduler)
    }

    companion object {
        /** 실제 구현체(Firebase / SharedPreferences / WorkManager)를 연결한 컨테이너. */
        fun create(app: Application): AppContainer {
            // 여러 곳이 함께 쓰는 것은 같은 인스턴스를 공유한다
            val auth by lazy { FirebaseAuthRepository(app) }
            val location by lazy { FusedLocationProvider(app) }
            val db by lazy { Firebase.firestore }
            val cachePrefs by lazy {
                app.getSharedPreferences(SharedPrefsVersionStore.FILE_NAME, Context.MODE_PRIVATE)
            }

            // Firestore 컬렉션 하나를 버전 키로 캐싱하는 캐시. 버전은 컬렉션 이름으로 만든 키로 기기에 기억한다.
            fun <T> versionedCache(collection: String, metaDocId: String, parser: (DocumentSnapshot) -> T?) =
                VersionedCache(
                    source = FirestoreCollectionSource(db, collection, metaDocId, parser),
                    versions = SharedPrefsVersionStore(cachePrefs, SharedPrefsVersionStore.keyFor(collection)),
                )

            return AppContainer(
                createAuthRepository = { auth },
                createUserRepository = { UserRepositoryImpl(auth) },
                createQuestRepository = {
                    QuestRepositoryImpl(
                        questsCache = versionedCache(FirestorePaths.QUESTS, FirestorePaths.QUESTS_META_DOC) {
                            it.toObject(Quest::class.java)
                        },
                        places = PlaceRepositoryImpl(
                            versionedCache(FirestorePaths.PLACES, FirestorePaths.PLACES_META_DOC) {
                                it.toObject(Place::class.java)
                            }
                        ),
                        location = location,
                        weather = KmaWeatherProvider(location, NetworkClient.kmaService, BuildConfig.KMA_API_KEY),
                        ranker = GeminiQuestRanker(GeminiClient.service, BuildConfig.GEMINI_API_KEY),
                    )
                },
                createActiveQuestStore = { SharedPrefsActiveQuestStore(app) },
                createSettingsRepository = { SharedPrefsSettingsRepository.create(app) },
                createNotificationScheduler = { WorkManagerNotificationScheduler(app) },
                createLocationProvider = { location },
            )
        }
    }
}
