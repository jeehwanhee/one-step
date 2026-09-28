package com.jeepark.onestep.data.model

import com.google.firebase.firestore.IgnoreExtraProperties

// `quests` 문서에는 앱이 쓰기만 하는 통계용 필드 giveUpReasons가 있다([QuestFields.GIVE_UP_REASONS]).
// 읽을 필요가 없어 모델에 두지 않고, @IgnoreExtraProperties로 모델에 없는 필드는 조용히 넘어간다.
@IgnoreExtraProperties
data class Quest(
    val index: Int = 0,
    val questName: String = "",
    val difficulty: Int = 1,
    val confirmQuestion: String = "",
    val questEXP: Int = 0,
)

@IgnoreExtraProperties
data class PrevQuest(
    val questName: String = "",
    val questEXP: Int = 0,
    val difficulty: Int = 1,
    val confirmQuestion: String = "",
    val confirmAnswer: String = "",
    val doneDate: String = "",
)
