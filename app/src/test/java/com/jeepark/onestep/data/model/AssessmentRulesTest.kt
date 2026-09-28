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

    // ===== 옛 필드와 분리 =====

    @Test
    fun `옛 isolatedCount 값은 판단에 쓰이지 않는다`() {
        val user = User(isolatedHistory = surveyed, questsSinceAssessment = 0, isolatedCount = 99)

        assertFalse(needsAssessment(user))
    }

    // ===== 시나리오: 설문 → 퀘스트 → 재설문 =====

    @Test
    fun `설문 후 퀘스트를 채워가면 정확히 10번째 완료에서 다시 설문이 필요해진다`() {
        val afterSurvey = User(isolatedHistory = surveyed, questsSinceAssessment = 0)

        assertFalse(needsAssessment(completeQuests(afterSurvey, count = 9)))
        assertTrue(needsAssessment(completeQuests(afterSurvey, count = 10)))
    }

    @Test
    fun `재설문 후에는 다시 10개를 채워야 설문이 필요해진다`() {
        val dueForSurvey = completeQuests(User(isolatedHistory = surveyed), count = 10)
        assertTrue(needsAssessment(dueForSurvey))

        // 설문 제출 = 이력 추가 + 카운트 0 (저장소에서 한 번의 update로 처리)
        val resurveyed = dueForSurvey.copy(
            isolatedHistory = dueForSurvey.isolatedHistory + IsolatedRecord(score = 40, recordedAt = 2L),
            questsSinceAssessment = 0,
        )

        assertFalse(needsAssessment(resurveyed))
        assertFalse(needsAssessment(completeQuests(resurveyed, count = 9)))
        assertTrue(needsAssessment(completeQuests(resurveyed, count = 10)))
    }
}
