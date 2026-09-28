package com.jeepark.onestep.data.repository

import com.jeepark.onestep.data.model.Gender
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
        gender: Gender,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    )

    fun getUser(
        onSuccess: (User) -> Unit,
        onFailure: (Exception) -> Unit
    )

    /**
     * 고립도 설문 제출: 답변·고립도 점수·점수 이력을 저장하고 `questsSinceAssessment`를 0으로 되돌린다
     * (한 번의 update라 이력 추가와 카운트 리셋이 함께 반영된다). 로그인 정보가 없으면 [onFailure]를 호출한다.
     */
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

    /** 퀘스트 완료 결과 저장. 완료 기록을 추가하고 `questsSinceAssessment`를 1 올린다. */
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
