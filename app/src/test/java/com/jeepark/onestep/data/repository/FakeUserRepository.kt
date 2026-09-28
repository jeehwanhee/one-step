package com.jeepark.onestep.data.repository

import com.jeepark.onestep.data.model.Gender
import com.jeepark.onestep.data.model.InitQuestions
import com.jeepark.onestep.data.model.IsolatedRecord
import com.jeepark.onestep.data.model.PrevQuest
import com.jeepark.onestep.data.model.User

/**
 * 테스트용 인메모리 가짜 UserRepository. Firebase 없이 동작하며,
 * 모든 콜백은 (실제 Firestore와 달리) 동기적으로 즉시 호출된다.
 */
class FakeUserRepository(
    var user: User? = null,
    var shouldFail: Boolean = false,
    var failureMessage: String = "테스트 실패"
) : UserRepository {

    /** 설문 제출 시 실제 구현이 모델로 계산하는 고립도 점수 대신 기록할 값. */
    var isolationScore: Int = 50

    override fun saveInitUser(
        nickname: String,
        age: Int,
        gender: Gender,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        if (shouldFail) {
            onFailure(Exception(failureMessage))
            return
        }
        user = (user ?: User()).copy(nickname = nickname, age = age, gender = gender.storedValue)
        onSuccess()
    }

    override fun getUser(onSuccess: (User) -> Unit, onFailure: (Exception) -> Unit) {
        val current = user
        if (shouldFail || current == null) {
            onFailure(Exception(failureMessage))
        } else {
            onSuccess(current)
        }
    }

    override fun saveInitQuestions(
        data: InitQuestions,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val current = user
        // 실제 구현처럼 사용자 문서를 읽지 못하면(로그인 없음·문서 없음 포함) 실패
        if (shouldFail || current == null) {
            onFailure(Exception(failureMessage))
            return
        }
        val now = System.currentTimeMillis()
        user = current.copy(
            initQuestions = data,
            isolated = isolationScore,
            isolatedLastModified = now,
            questsSinceAssessment = 0,
            isolatedHistory = current.isolatedHistory + IsolatedRecord(score = isolationScore, recordedAt = now),
        )
        onSuccess()
    }

    override fun getUserFromServer(uid: String, onResult: (User?) -> Unit, onError: () -> Unit) {
        if (shouldFail) {
            onError()
            return
        }
        onResult(user)
    }

    override fun updateLastAccessDate(uid: String, timestamp: Long) {
        user = user?.copy(lastAccessDate = timestamp)
    }

    override fun incrementDailyQuestCount(
        uid: String,
        sameDayAsLast: Boolean,
        today: String,
        onSuccess: () -> Unit
    ) {
        user?.let { current ->
            user = if (sameDayAsLast) {
                current.copy(dailyQuestCount = current.dailyQuestCount + 1)
            } else {
                current.copy(dailyQuestCount = 1, dailyQuestDate = today)
            }
        }
        onSuccess()
    }

    override fun applyQuestCompletion(
        uid: String,
        progress: Int,
        tier: Int,
        difficultyQueue: List<Double>,
        resultsQueue: List<Int>,
        prevQuestMap: Map<String, Any>,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        if (shouldFail) {
            onFailure(Exception(failureMessage))
            return
        }
        user = user?.let { current ->
            current.copy(
                progress = progress,
                tier = tier,
                difficultyQueue = difficultyQueue,
                questResultsQueue = resultsQueue,
                prevQuests = current.prevQuests + prevQuestMap.toPrevQuest(),
                questsSinceAssessment = current.questsSinceAssessment + 1,
            )
        }
        onSuccess()
    }

    override fun applyGiveUp(
        uid: String,
        resultsQueue: List<Int>,
        giveUpEntry: Map<String, Any>,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        if (shouldFail) {
            onFailure(Exception(failureMessage))
            return
        }
        user = user?.copy(questResultsQueue = resultsQueue)
        onSuccess()
    }

    override fun updateNotificationAgreed(uid: String, agreed: Boolean) {
        user = user?.copy(notificationAgreed = agreed)
    }

    override fun deleteUser(uid: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        if (shouldFail) {
            onFailure(Exception(failureMessage))
            return
        }
        user = null
        onSuccess()
    }

    // Firestore에 저장되는 완료 기록 Map(QuestCompletion.toPrevQuestMap)을 다시 모델로
    private fun Map<String, Any>.toPrevQuest() = PrevQuest(
        questName       = this["questName"] as String,
        questEXP        = this["questEXP"] as Int,
        difficulty      = this["difficulty"] as Int,
        confirmQuestion = this["confirmQuestion"] as String,
        confirmAnswer   = this["confirmAnswer"] as String,
        doneDate        = this["doneDate"] as String,
    )
}
