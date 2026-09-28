package com.jeepark.onestep.data.repository

import com.jeepark.onestep.data.model.GiveUpReason
import com.jeepark.onestep.data.model.Quest

/** 테스트용 가짜 QuestRepository. 네트워크/Firestore 없이 미리 정해둔 결과를 돌려준다. */
class FakeQuestRepository : QuestRepository {
    var quests: List<Quest> = emptyList()
    var fetchError: Exception? = null
    var giveUpFailure: Exception? = null

    var lastRatios: List<Double>? = null
    var lastUseGemini: Boolean? = null
    val giveUpRecords = mutableListOf<Pair<Int, GiveUpReason>>() // (questIndex, reason)

    override suspend fun fetchFilteredQuests(ratios: List<Double>, useGemini: Boolean): List<Quest> {
        lastRatios = ratios
        lastUseGemini = useGemini
        fetchError?.let { throw it }
        return quests
    }

    override fun recordGiveUp(questIndex: Int, reason: GiveUpReason, onFailure: (Exception) -> Unit) {
        giveUpRecords.add(questIndex to reason)
        giveUpFailure?.let(onFailure)
    }
}
