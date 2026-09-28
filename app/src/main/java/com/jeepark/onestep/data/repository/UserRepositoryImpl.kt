package com.jeepark.onestep.data.repository

import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.firestore
import com.jeepark.onestep.data.model.Gender
import com.jeepark.onestep.data.model.InitQuestions
import com.jeepark.onestep.data.model.User
import com.jeepark.onestep.data.model.isolationScore

class UserRepositoryImpl(private val auth: AuthRepository) : UserRepository {
    private val db = Firebase.firestore

    override fun saveInitUser(
        nickname: String,
        age: Int,
        gender: Gender,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val uid = auth.currentUid
        if (uid == null) {
            onFailure(IllegalStateException("로그인된 사용자가 없습니다"))
            return
        }
        val email = auth.currentEmail
        if (email == null) {
            onFailure(IllegalStateException("사용자 이메일을 가져올 수 없습니다"))
            return
        }

        val user = User(
            uid= uid,
            email= email,
            nickname=nickname,
            age=age,
            gender=gender.storedValue,
        )
        db.collection("users").document(uid)
            .set(user)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it) }
    }



    override fun getUser(
        onSuccess: (User) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val uid = auth.currentUid
        if (uid == null) {
            onFailure(IllegalStateException("로그인된 사용자가 없습니다"))
            return
        }

        db.collection("users").document(uid).get()
            .addOnSuccessListener { document ->
                try {
                    val user = document.toObject(User::class.java)
                    if (user != null) onSuccess(user)
                    else onFailure(Exception("User null"))
                } catch (e: Exception) {
                    onFailure(e)
                }
            }
            .addOnFailureListener { onFailure(it) }
    }

    override fun saveInitQuestions(
        data: InitQuestions,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val uid = auth.currentUid
        if (uid == null) {
            onFailure(IllegalStateException("로그인된 사용자가 없습니다"))
            return
        }
        getUser(
            onSuccess = { user ->
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
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener {
                        onFailure(it)
                    }
            },
            onFailure = { onFailure(it) }
        )
    }

    override fun getUserFromServer(
        uid: String,
        onResult: (User?) -> Unit,
        onError: () -> Unit
    ) {
        db.collection("users").document(uid)
            .get(Source.SERVER)
            .addOnSuccessListener { doc ->
                val user = try {
                    if (doc.exists()) doc.toObject(User::class.java) else null
                } catch (e: Exception) {
                    null
                }
                onResult(user)
            }
            .addOnFailureListener { onError() }
    }

    override fun updateLastAccessDate(uid: String, timestamp: Long) {
        db.collection("users").document(uid).update("lastAccessDate", timestamp)
    }

    override fun incrementDailyQuestCount(
        uid: String,
        sameDayAsLast: Boolean,
        today: String,
        onSuccess: () -> Unit
    ) {
        val update: Map<String, Any> = if (sameDayAsLast) {
            mapOf("dailyQuestCount" to FieldValue.increment(1))
        } else {
            mapOf("dailyQuestCount" to 1, "dailyQuestDate" to today)
        }
        db.collection("users").document(uid).update(update)
            .addOnSuccessListener { onSuccess() }
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
        db.collection("users").document(uid).update(
            mapOf(
                "progress" to progress,
                "tier" to tier,
                "difficultyQueue" to difficultyQueue,
                "questResultsQueue" to resultsQueue,
                "prevQuests" to FieldValue.arrayUnion(prevQuestMap),
                "questsSinceAssessment" to FieldValue.increment(1)
            )
        ).addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it) }
    }

    override fun applyGiveUp(
        uid: String,
        resultsQueue: List<Int>,
        giveUpEntry: Map<String, Any>,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        db.collection("users").document(uid).update(
            mapOf(
                "questResultsQueue" to resultsQueue,
                "giveUpReasons" to FieldValue.arrayUnion(giveUpEntry)
            )
        ).addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it) }
    }

    override fun updateNotificationAgreed(uid: String, agreed: Boolean) {
        db.collection("users").document(uid).update("notificationAgreed", agreed)
    }

    override fun deleteUser(uid: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        db.collection("users").document(uid).delete()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it) }
    }
}
