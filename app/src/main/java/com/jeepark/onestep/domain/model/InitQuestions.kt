package com.jeepark.onestep.domain.model

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class InitQuestions(
    val shower: Int = 0,
    val meal: Int = 0,
    val sleepTime: Int = 0,
    val outside: Int = 0,
    val hiki: Int = 0,
    val activeTime: Int = 0,
)
