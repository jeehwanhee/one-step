package com.jeepark.onestep.data.repository

import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.firestore
import com.jeepark.onestep.domain.model.FirestorePaths
import com.jeepark.onestep.domain.model.Gender
import com.jeepark.onestep.domain.model.InitQuestions
import com.jeepark.onestep.domain.model.IsolatedRecordFields
import com.jeepark.onestep.domain.model.User
import com.jeepark.onestep.domain.model.UserFields
import com.jeepark.onestep.domain.service.isolationScore
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl(private val auth: AuthRepository) : UserRepository {
    private val db = Firebase.firestore

    private fun requireUid(): String =
        auth.currentUid ?: throw IllegalStateException("로그인된 사용자가 없습니다")

    private fun userDoc(uid: String) = db.collection(FirestorePaths.USERS).document(uid)

    override suspend fun saveInitUser(nickname: String, age: Int, gender: Gender): Result<Unit> = suspendRunCatching {
        val uid = requireUid()
        val email = auth.currentEmail ?: throw IllegalStateException("사용자 이메일을 가져올 수 없습니다")

        val user = User(
            uid = uid,
            email = email,
            nickname = nickname,
            age = age,
            gender = gender.storedValue,
        )
        userDoc(uid).set(user).awaitCompletion()
    }

    override suspend fun getUser(): Result<User> = suspendRunCatching {
        val uid = requireUid()
        val document = userDoc(uid).get().await()
        document.toObject(User::class.java) ?: throw Exception("User null")
    }

    override suspend fun saveInitQuestions(data: InitQuestions): Result<Unit> = suspendRunCatching {
        val uid = requireUid()
        val user = getUser().getOrThrow()

        val score = isolationScore(user.age, Gender.fromStored(user.gender), data)
        val now = System.currentTimeMillis()
        val historyEntry = mapOf(
            IsolatedRecordFields.SCORE to score,
            IsolatedRecordFields.RECORDED_AT to now
        )

        userDoc(uid)
            .update(
                UserFields.INIT_QUESTIONS, data,
                UserFields.ISOLATED, score,
                UserFields.ISOLATED_LAST_MODIFIED, now,
                UserFields.QUESTS_SINCE_ASSESSMENT, 0,
                UserFields.ISOLATED_HISTORY, FieldValue.arrayUnion(historyEntry)
            )
            .awaitCompletion()
    }

    override suspend fun getUserFromServer(uid: String): Result<User?> = suspendRunCatching {
        val doc = userDoc(uid).get(Source.SERVER).await()
        // 문서가 없으면 null(신규 사용자). 문서를 읽지 못한 경우(예: 모델 변환 실패)는 예외로 실패 처리해서
        // 기존 사용자를 신규 사용자로 착각해 가입 화면으로 보내지 않는다.
        if (doc.exists()) doc.toObject(User::class.java) else null
    }

    override suspend fun updateLastAccessDate(uid: String, timestamp: Long): Result<Unit> = suspendRunCatching {
        userDoc(uid).update(UserFields.LAST_ACCESS_DATE, timestamp).awaitCompletion()
    }

    override suspend fun incrementDailyQuestCount(
        uid: String,
        sameDayAsLast: Boolean,
        today: String
    ): Result<Unit> = suspendRunCatching {
        val update: Map<String, Any> = if (sameDayAsLast) {
            mapOf(UserFields.DAILY_QUEST_COUNT to FieldValue.increment(1))
        } else {
            mapOf(UserFields.DAILY_QUEST_COUNT to 1, UserFields.DAILY_QUEST_DATE to today)
        }
        userDoc(uid).update(update).awaitCompletion()
    }

    override suspend fun applyQuestCompletion(
        uid: String,
        progress: Int,
        tier: Int,
        difficultyQueue: List<Double>,
        resultsQueue: List<Int>,
        prevQuestMap: Map<String, Any>
    ): Result<Unit> = suspendRunCatching {
        userDoc(uid).update(
            mapOf(
                UserFields.PROGRESS to progress,
                UserFields.TIER to tier,
                UserFields.DIFFICULTY_QUEUE to difficultyQueue,
                UserFields.QUEST_RESULTS_QUEUE to resultsQueue,
                UserFields.PREV_QUESTS to FieldValue.arrayUnion(prevQuestMap),
                UserFields.QUESTS_SINCE_ASSESSMENT to FieldValue.increment(1)
            )
        ).awaitCompletion()
    }

    override suspend fun applyGiveUp(
        uid: String,
        resultsQueue: List<Int>,
        giveUpEntry: Map<String, Any>
    ): Result<Unit> = suspendRunCatching {
        userDoc(uid).update(
            mapOf(
                UserFields.QUEST_RESULTS_QUEUE to resultsQueue,
                UserFields.GIVE_UP_REASONS to FieldValue.arrayUnion(giveUpEntry)
            )
        ).awaitCompletion()
    }

    override suspend fun updateNotificationAgreed(uid: String, agreed: Boolean): Result<Unit> = suspendRunCatching {
        userDoc(uid).update(UserFields.NOTIFICATION_AGREED, agreed).awaitCompletion()
    }

    override suspend fun deleteUser(uid: String): Result<Unit> = suspendRunCatching {
        userDoc(uid).delete().awaitCompletion()
    }
}
