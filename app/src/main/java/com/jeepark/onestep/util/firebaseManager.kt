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
        if (currentUser == null) {
            onFailure(IllegalStateException("로그인된 사용자가 없습니다"))
            return
        }
        val uid = currentUser.uid
        val email = currentUser.email
        if (email == null) {
            onFailure(IllegalStateException("사용자 이메일을 가져올 수 없습니다"))
            return
        }

        val user = User(
            uid= uid,
            email= email,
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
        val uid = auth.currentUser?.uid
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
                // 학습 분포 범위를 벗어난 입력은 경계값으로 clamp (외삽 방지)
                val inputData = doubleArrayOf(
                    user.age.toDouble().coerceIn(19.0, 38.0),    // 0: age (학습 19~38)
                    if (user.gender) 0.0 else 1.0,               // 1: gender (남=0.0, 여=1.0)
                    data.shower.toDouble().coerceIn(0.0, 7.0),   // 2: shower (주간 0~7)
                    outFreqScaled,                               // 3: out_freq (매핑상 0~4)
                    data.activeTime.toDouble().coerceIn(0.0, 3.0), // 4: active_time (0~3)
                    data.hiki.toDouble().coerceIn(0.0, 240.0),   // 5: hiki_period (0~240개월)
                    data.sleepTime.toDouble().coerceIn(2.0, 20.0), // 6: sleep_hours (2~20)
                    data.meal.toDouble().coerceIn(0.0, 4.0)      // 7: meal_count (0~4)
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
