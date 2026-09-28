package com.jeepark.onestep.data.repository

import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.firestore
import com.jeepark.onestep.data.model.Gender
import com.jeepark.onestep.data.model.InitQuestions
import com.jeepark.onestep.data.model.User
import com.jeepark.onestep.data.model.isolationScore
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl(private val auth: AuthRepository) : UserRepository {
    private val db = Firebase.firestore

    private fun requireUid(): String =
        auth.currentUid ?: throw IllegalStateException("로그인된 사용자가 없습니다")

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
        db.collection("users").document(uid).set(user).awaitCompletion()
    }

    override suspend fun getUser(): Result<User> = suspendRunCatching {
        val uid = requireUid()
        val document = db.collection("users").document(uid).get().await()
        document.toObject(User::class.java) ?: throw Exception("User null")
    }

    override suspend fun saveInitQuestions(data: InitQuestions): Result<Unit> = suspendRunCatching {
        val uid = requireUid()
        val user = getUser().getOrThrow()

        val score = isolationScore(user.age, Gender.fromStored(user.gender), data)
        val now = System.currentTimeMillis()
        val historyEntry = mapOf(
            "score" to score,
            "recordedAt" to now
        )

        db.collection("users").document(uid)
            .update(
                "initQuestions", data,
                "isolated", score,
                "isolatedLastModified", now,
                "questsSinceAssessment", 0,
                "isolatedHistory", FieldValue.arrayUnion(historyEntry)
            )
            .awaitCompletion()
    }

    override suspend fun getUserFromServer(uid: String): Result<User?> = suspendRunCatching {
        val doc = db.collection("users").document(uid).get(Source.SERVER).await()
        // 문서가 없으면 null(신규 사용자). 문서를 읽지 못한 경우(예: 모델 변환 실패)는 예외로 실패 처리해서
        // 기존 사용자를 신규 사용자로 착각해 가입 화면으로 보내지 않는다.
        if (doc.exists()) doc.toObject(User::class.java) else null
    }

    override suspend fun updateLastAccessDate(uid: String, timestamp: Long): Result<Unit> = suspendRunCatching {
        db.collection("users").document(uid).update("lastAccessDate", timestamp).awaitCompletion()
    }

    override suspend fun incrementDailyQuestCount(
        uid: String,
        sameDayAsLast: Boolean,
        today: String
    ): Result<Unit> = suspendRunCatching {
        val update: Map<String, Any> = if (sameDayAsLast) {
            mapOf("dailyQuestCount" to FieldValue.increment(1))
        } else {
            mapOf("dailyQuestCount" to 1, "dailyQuestDate" to today)
        }
        db.collection("users").document(uid).update(update).awaitCompletion()
    }

    override suspend fun applyQuestCompletion(
        uid: String,
        progress: Int,
        tier: Int,
        difficultyQueue: List<Double>,
        resultsQueue: List<Int>,
        prevQuestMap: Map<String, Any>
    ): Result<Unit> = suspendRunCatching {
        db.collection("users").document(uid).update(
            mapOf(
                "progress" to progress,
                "tier" to tier,
                "difficultyQueue" to difficultyQueue,
                "questResultsQueue" to resultsQueue,
                "prevQuests" to FieldValue.arrayUnion(prevQuestMap),
                "questsSinceAssessment" to FieldValue.increment(1)
            )
        ).awaitCompletion()
    }

    override suspend fun applyGiveUp(
        uid: String,
        resultsQueue: List<Int>,
        giveUpEntry: Map<String, Any>
    ): Result<Unit> = suspendRunCatching {
        db.collection("users").document(uid).update(
            mapOf(
                "questResultsQueue" to resultsQueue,
                "giveUpReasons" to FieldValue.arrayUnion(giveUpEntry)
            )
        ).awaitCompletion()
    }

    override suspend fun updateNotificationAgreed(uid: String, agreed: Boolean): Result<Unit> = suspendRunCatching {
        db.collection("users").document(uid).update("notificationAgreed", agreed).awaitCompletion()
    }

    override suspend fun deleteUser(uid: String): Result<Unit> = suspendRunCatching {
        db.collection("users").document(uid).delete().awaitCompletion()
    }
}
