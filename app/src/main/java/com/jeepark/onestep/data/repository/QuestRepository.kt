package com.jeepark.onestep.data.repository

import com.jeepark.onestep.data.model.GiveUpReason
import com.jeepark.onestep.data.model.Quest

/**
 * 퀘스트 조회/기록 인터페이스. MainViewModel은 이 인터페이스에만 의존하고,
 * 테스트에서는 FakeQuestRepository로 교체할 수 있다.
 */
interface QuestRepository {
    suspend fun fetchFilteredQuests(
        ratios: List<Double>,
        useGemini: Boolean = true
    ): List<Quest>

    /** 해당 퀘스트 문서에 포기 사유를 기록 (통계용, 실패해도 유저 쪽 기록에는 영향 없음). */
    fun recordGiveUp(questIndex: Int, reason: GiveUpReason, onFailure: (Exception) -> Unit = {})
}
