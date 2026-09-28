package com.jeepark.onestep.di

import android.app.Application
import com.jeepark.onestep.data.repository.ActiveQuestStore
import com.jeepark.onestep.data.repository.QuestRepository
import com.jeepark.onestep.data.repository.QuestRepositoryImpl
import com.jeepark.onestep.data.repository.SharedPrefsActiveQuestStore
import com.jeepark.onestep.data.repository.UserRepository
import com.jeepark.onestep.data.repository.UserRepositoryImpl

/**
 * 앱 전체가 함께 쓰는 의존성을 한 곳에서 만들고 보관한다(수동 DI, 별도 라이브러리 없음).
 * 각 항목은 처음 꺼낼 때 한 번만 만들어져서, 쓰지 않는 저장소는 Firebase에 접근하지 않는다.
 *
 * 생성 함수를 주입받으므로 테스트에서는 가짜 저장소로 채울 수 있다. 실제 구현 연결은 [create].
 * 새 저장소·서비스가 생기면 여기에 추가하고, ViewModel 팩토리는 이 컨테이너에서 꺼내 쓴다.
 */
class AppContainer(
    createUserRepository: () -> UserRepository,
    createQuestRepository: () -> QuestRepository,
    createActiveQuestStore: () -> ActiveQuestStore,
) {
    val userRepository: UserRepository by lazy(createUserRepository)
    val questRepository: QuestRepository by lazy(createQuestRepository)
    val activeQuestStore: ActiveQuestStore by lazy(createActiveQuestStore)

    companion object {
        /** 실제 구현체(Firestore / SharedPreferences)를 연결한 컨테이너. */
        fun create(app: Application): AppContainer = AppContainer(
            createUserRepository = { UserRepositoryImpl() },
            createQuestRepository = { QuestRepositoryImpl(app) },
            createActiveQuestStore = { SharedPrefsActiveQuestStore(app) },
        )
    }
}
