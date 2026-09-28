package com.jeepark.onestep.data.model

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class AssessmentRulesTest {

    private val surveyed = listOf(IsolatedRecord(score = 50, recordedAt = 1L))

    private val quest = Quest(index = 1, questName = "산책", difficulty = 2, confirmQuestion = "어땠나요?", questEXP = 1)

    /** 퀘스트를 [count]개 완료한 뒤의 사용자. */
    private fun completeQuests(user: User, count: Int): User =
        (1..count).fold(user) { current, i ->
            computeQuestCompletion(current, quest, "답$i", "2026.09.28 10:00:0$i").applyTo(current)
        }

    // ===== 규칙 =====

    @Test
    fun `한 번도 설문하지 않았으면 설문이 필요하다`() {
        assertTrue(needsAssessment(User(isolatedHistory = emptyList())))
    }

    // ===== 시나리오: 설문 → 퀘스트 → 재설문 =====

    @Test
    fun `설문 후 퀘스트를 채워가면 정확히 10번째 완료에서 다시 설문이 필요해진다`() {
        val afterSurvey = User(isolatedHistory = surveyed, questsSinceAssessment = 0)

        assertFalse(needsAssessment(completeQuests(afterSurvey, count = 9)))
        assertTrue(needsAssessment(completeQuests(afterSurvey, count = 10)))
    }
}
