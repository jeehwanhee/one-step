package com.jeepark.onestep.di

import android.app.Application
import com.jeepark.onestep.data.repository.AccountService
import com.jeepark.onestep.data.repository.ActiveQuestStore
import com.jeepark.onestep.data.repository.AuthRepository
import com.jeepark.onestep.data.repository.FirebaseAuthRepository
import com.jeepark.onestep.data.repository.QuestRepository
import com.jeepark.onestep.data.repository.QuestRepositoryImpl
import com.jeepark.onestep.data.repository.SettingsRepository
import com.jeepark.onestep.data.repository.SharedPrefsActiveQuestStore
import com.jeepark.onestep.data.repository.SharedPrefsSettingsRepository
import com.jeepark.onestep.data.repository.UserRepository
import com.jeepark.onestep.data.repository.UserRepositoryImpl
import com.jeepark.onestep.util.NotificationScheduler
import com.jeepark.onestep.util.WorkManagerNotificationScheduler

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
) {
    val authRepository: AuthRepository by lazy(createAuthRepository)
    val userRepository: UserRepository by lazy(createUserRepository)
    val questRepository: QuestRepository by lazy(createQuestRepository)
    val activeQuestStore: ActiveQuestStore by lazy(createActiveQuestStore)
    val settingsRepository: SettingsRepository by lazy(createSettingsRepository)
    val notificationScheduler: NotificationScheduler by lazy(createNotificationScheduler)

    /** 로그아웃·계정 삭제. 위의 저장소들을 조합한다. */
    val accountService: AccountService by lazy {
        AccountService(authRepository, userRepository, settingsRepository, notificationScheduler)
    }

    companion object {
        /** 실제 구현체(Firebase / SharedPreferences / WorkManager)를 연결한 컨테이너. */
        fun create(app: Application): AppContainer {
            // 사용자 저장소가 인증 저장소를 함께 쓰므로 같은 인스턴스를 공유한다
            val auth by lazy { FirebaseAuthRepository(app) }
            return AppContainer(
                createAuthRepository = { auth },
                createUserRepository = { UserRepositoryImpl(auth) },
                createQuestRepository = { QuestRepositoryImpl(app) },
                createActiveQuestStore = { SharedPrefsActiveQuestStore(app) },
                createSettingsRepository = { SharedPrefsSettingsRepository.create(app) },
                createNotificationScheduler = { WorkManagerNotificationScheduler(app) },
            )
        }
    }
}
