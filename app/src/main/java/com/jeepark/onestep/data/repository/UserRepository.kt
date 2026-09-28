package com.jeepark.onestep.data.repository

import com.jeepark.onestep.domain.model.Gender
import com.jeepark.onestep.domain.model.InitQuestions
import com.jeepark.onestep.domain.model.User

/**
 * 사용자 데이터 접근 인터페이스. ViewModel은 이 인터페이스에만 의존하고,
 * 테스트에서는 FakeUserRepository로 교체할 수 있다.
 *
 * 모든 메서드는 `suspend`이고 성공·실패를 [Result]로 돌려준다(취소는 예외로 전파된다).
 * 쓰기는 Firestore 서버가 확인한 뒤에 끝난다. 오프라인이면 확인이 올 때까지 끝나지 않으므로,
 * 결과를 기다릴 필요가 없는 호출(접속일 갱신 등)은 호출하는 쪽이 별도 코루틴으로 띄운다.
 */
interface UserRepository {
    /** 가입 정보로 사용자 문서를 새로 만든다. 로그인 정보나 이메일이 없으면 실패한다. */
    suspend fun saveInitUser(nickname: String, age: Int, gender: Gender): Result<Unit>

    /** 로그인한 사용자의 문서. 로그인하지 않았거나 문서를 읽을 수 없으면 실패한다. */
    suspend fun getUser(): Result<User>

    /**
     * 고립도 설문 제출: 답변·고립도 점수·점수 이력을 저장하고 `questsSinceAssessment`를 0으로 되돌린다
     * (한 번의 update라 이력 추가와 카운트 리셋이 함께 반영된다). 로그인 정보가 없으면 실패한다.
     */
    suspend fun saveInitQuestions(data: InitQuestions): Result<Unit>

    /**
     * 캐시가 아닌 서버에서 강제 조회 (신규/기존 사용자 판별처럼 최신 상태가 중요한 경우 사용).
     * 문서가 없으면 성공이면서 null이다. 네트워크 오류나 문서를 읽을 수 없는 경우는 실패이며,
     * 신규 사용자로 착각하면 안 되는 경우(null)와 구분된다.
     */
    suspend fun getUserFromServer(uid: String): Result<User?>

    suspend fun updateLastAccessDate(uid: String, timestamp: Long): Result<Unit>

    suspend fun incrementDailyQuestCount(uid: String, sameDayAsLast: Boolean, today: String): Result<Unit>

    /** 퀘스트 완료 결과 저장. 완료 기록을 추가하고 `questsSinceAssessment`를 1 올린다. */
    suspend fun applyQuestCompletion(
        uid: String,
        progress: Int,
        tier: Int,
        difficultyQueue: List<Double>,
        resultsQueue: List<Int>,
        prevQuestMap: Map<String, Any>,
    ): Result<Unit>

    suspend fun applyGiveUp(uid: String, resultsQueue: List<Int>, giveUpEntry: Map<String, Any>): Result<Unit>

    suspend fun updateNotificationAgreed(uid: String, agreed: Boolean): Result<Unit>

    suspend fun deleteUser(uid: String): Result<Unit>
}
