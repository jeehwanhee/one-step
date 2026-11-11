package com.jeepark.onestep.data.repository

import com.jeepark.onestep.domain.model.Gender
import com.jeepark.onestep.domain.model.InitQuestions
import com.jeepark.onestep.domain.model.IsolatedRecord
import com.jeepark.onestep.domain.model.PrevQuest
import com.jeepark.onestep.domain.model.User
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

/**
 * 테스트용 인메모리 가짜 UserRepository. Firebase 없이 동작한다.
 * 기본적으로 즉시 응답하고, [writeGate]를 설정하면 쓰기 응답을 원하는 시점까지 미룰 수 있다.
 * Firestore처럼 쓰기는 서버 확인 전에 바로 반영되어 [observeUser] 구독자에게 전달된다(실패하는 쓰기는 반영하지 않는다).
 */
class FakeUserRepository(
    user: User? = null,
    var shouldFail: Boolean = false,
    var failureMessage: String = "테스트 실패"
) : UserRepository {

    private val userState = MutableStateFlow(user)

    /** 저장된 사용자 문서. 바꾸면 다른 화면이 문서를 고친 것처럼 구독자에게 전달된다. */
    var user: User?
        get() = userState.value
        set(value) { userState.value = value }

    /** 설문 제출 시 실제 구현이 모델로 계산하는 고립도 점수 대신 기록할 값. */
    var isolationScore: Int = 50

    /**
     * 설정하면 쓰기 계열 호출이 이 게이트가 열릴 때까지 끝나지 않는다.
     * 서버 확인이 늦거나 오프라인인 상황을 흉내 내서, 응답을 기다리면 안 되는 호출이 실제로 기다리지 않는지 확인하는 데 쓴다.
     */
    var writeGate: CompletableDeferred<Unit>? = null

    private fun failure(): Result<Nothing> = Result.failure(Exception(failureMessage))

    /** Firestore처럼 실패하지 않는 쓰기는 먼저 반영하고, 서버 확인([writeGate])이 오면 끝난다. */
    private suspend fun write(fails: Boolean = shouldFail, change: () -> Unit): Result<Unit> {
        if (fails) return failure()
        change()
        writeGate?.await()
        return Result.success(Unit)
    }

    override suspend fun saveInitUser(nickname: String, age: Int, gender: Gender): Result<Unit> = write {
        user = (user ?: User()).copy(nickname = nickname, age = age, gender = gender.storedValue)
    }

    override suspend fun getUser(): Result<User> {
        val current = user
        return if (shouldFail || current == null) failure() else Result.success(current)
    }

    override fun observeUser(): Flow<User> = flow {
        if (shouldFail) throw Exception(failureMessage)
        emitAll(userState.map { it ?: throw Exception(failureMessage) })
    }

    // 실제 구현처럼 사용자 문서를 읽지 못하면(로그인 없음·문서 없음 포함) 실패
    override suspend fun saveInitQuestions(data: InitQuestions): Result<Unit> =
        write(fails = shouldFail || user == null) {
            val current = checkNotNull(user)
            val now = System.currentTimeMillis()
            user = current.copy(
                initQuestions = data,
                isolated = isolationScore,
                isolatedLastModified = now,
                questsSinceAssessment = 0,
                isolatedHistory = current.isolatedHistory + IsolatedRecord(score = isolationScore, recordedAt = now),
            )
        }

    override suspend fun getUserFromServer(uid: String): Result<User?> =
        if (shouldFail) failure() else Result.success(user)

    override suspend fun updateLastAccessDate(uid: String, timestamp: Long): Result<Unit> = write(fails = false) {
        user = user?.copy(lastAccessDate = timestamp)
    }

    override suspend fun incrementDailyQuestCount(
        uid: String,
        sameDayAsLast: Boolean,
        today: String
    ): Result<Unit> = write(fails = false) {
        user = user?.let { current ->
            if (sameDayAsLast) {
                current.copy(dailyQuestCount = current.dailyQuestCount + 1)
            } else {
                current.copy(dailyQuestCount = 1, dailyQuestDate = today)
            }
        }
    }

    override suspend fun applyQuestCompletion(
        uid: String,
        progress: Int,
        tier: Int,
        difficultyQueue: List<Double>,
        resultsQueue: List<Int>,
        prevQuestMap: Map<String, Any>
    ): Result<Unit> = write {
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
    }

    override suspend fun applyGiveUp(
        uid: String,
        resultsQueue: List<Int>,
        giveUpEntry: Map<String, Any>
    ): Result<Unit> = write {
        user = user?.copy(questResultsQueue = resultsQueue)
    }

    override suspend fun updateNotificationAgreed(uid: String, agreed: Boolean): Result<Unit> = write(fails = false) {
        user = user?.copy(notificationAgreed = agreed)
    }

    override suspend fun deleteUser(uid: String): Result<Unit> = write {
        user = null
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
