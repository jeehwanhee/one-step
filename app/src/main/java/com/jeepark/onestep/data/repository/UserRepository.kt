package com.jeepark.onestep.data.repository

import com.jeepark.onestep.data.model.InitQuestions
import com.jeepark.onestep.data.model.User

/**
 * 사용자 데이터 접근 인터페이스. ViewModel은 이 인터페이스에만 의존하고,
 * 테스트에서는 FakeUserRepository로 교체할 수 있다.
 */
interface UserRepository {
    fun saveInitUser(
        nickname: String,
        age: Int,
        gender: Boolean,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    )

    fun getUser(
        onSuccess: (User) -> Unit,
        onFailure: (Exception) -> Unit
    )

    fun saveInitQuestions(
        data: InitQuestions,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    )

    /** 캐시가 아닌 서버에서 강제 조회 (신규/기존 사용자 판별처럼 최신 상태가 중요한 경우 사용). */
    fun getUserFromServer(
        uid: String,
        onResult: (User?) -> Unit,
        onError: () -> Unit
    )

    fun updateLastAccessDate(uid: String, timestamp: Long)

    fun incrementDailyQuestCount(
        uid: String,
        sameDayAsLast: Boolean,
        today: String,
        onSuccess: () -> Unit
    )

    fun applyQuestCompletion(
        uid: String,
        progress: Int,
        tier: Int,
        difficultyQueue: List<Double>,
        resultsQueue: List<Int>,
        prevQuestMap: Map<String, Any>,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    )

    fun resetIsolatedCount(uid: String, onSuccess: () -> Unit)

    fun applyGiveUp(
        uid: String,
        resultsQueue: List<Int>,
        giveUpEntry: Map<String, Any>,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    )

    fun updateNotificationAgreed(uid: String, agreed: Boolean)

    fun deleteUser(uid: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit)
}
