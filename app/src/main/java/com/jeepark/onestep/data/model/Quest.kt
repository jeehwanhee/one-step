package com.jeepark.onestep.data.model

data class Quest(
    val index: Int = 0,
    val questName: String = "",
    val difficulty: Int = 1,
    val confirmQuestion: String = "",
    val questEXP: Int = 0,
)

data class PrevQuest(
    val questName: String = "",
    val questEXP: Int = 0,
    val difficulty: Int = 1,
    val confirmQuestion: String = "",
    val confirmAnswer: String = "",
    val doneDate: String = "",
)
