package com.jeepark.onestep.util

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore
import com.jeepark.onestep.data.model.InitQuestions
import com.jeepark.onestep.data.model.User

class FirestoreRepository {
    private val db = Firebase.firestore
    private val auth = Firebase.auth

    fun saveInitUser(
        nickname: String,
        age: Int,
        gender: Boolean,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val currentUser = Firebase.auth.currentUser
        val uid = currentUser!!.uid
        val email = currentUser.email

        val user = User(
            uid= uid,
            email= email!!,
            nickname=nickname,
            age=age,
            gender=gender,
        )
        db.collection("users").document(uid)
            .set(user)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it) }
    }



    fun getUser(
        onSuccess: (User) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val uid = auth.currentUser?.uid ?: return

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

    fun saveInitQuestions(
        data: InitQuestions,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val uid = auth.currentUser?.uid ?: return
        val outFreqScaled = when (data.outside) {
            0 -> 0.0          // 전혀 안 나감 (0)
            1, 2 -> 1.0       // 주 1~2회 (1)
            3, 4 -> 2.0       // 주 3~4회 (2)
            5, 6 -> 3.0       // 주 5~6회 (3)
            7 -> 4.0          // 매일 나감 (4)
            else -> 0.0       // 예외 처리
        }
        getUser(
            onSuccess = { user ->
                val inputData = doubleArrayOf(
                    user.age.toDouble(),                 // 0: age
                    if (user.gender) 0.0 else 1.0,       // 1: gender (True:남자=0.0, False:여자=1.0) *학습 기준에 맞춤
                    data.member.toDouble(),              // 2: roommates
                    outFreqScaled,                       // 3: out_freq
                    data.activeTime.toDouble(),          // 4: active_time
                    data.hiki.toDouble(),                // 5: hiki_period
                    data.sleepTime.toDouble(),           // 6: sleep_hours
                    data.meal.toDouble()                 // 7: meal_count
                )
                val result = Model_A.predict(inputData)
                val score = (result * 100).toInt()
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
                        "isolatedCount", FieldValue.increment(1),
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
}
