package com.jeepark.onestep.domain.rules

import com.jeepark.onestep.domain.model.Quest
import com.jeepark.onestep.domain.model.User
import com.jeepark.onestep.domain.model.dailyQuota
import com.jeepark.onestep.domain.model.progression
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class QuestRulesTest {

    private val today = "2026-09-28"

    // ===== 일일 제한 =====

    @Test
    fun `오늘 20개면 한도에 도달한다`() {
        val user = User(dailyQuestDate = today, dailyQuestCount = DAILY_QUEST_LIMIT)
        assertTrue(hasReachedDailyLimit(user.dailyQuota(), today))
    }

    // ===== 퀘스트 완료 계산 =====

    private val quest = Quest(
        index = 7,
        questName = "공원 벤치에 앉기",
        difficulty = 3,
        confirmQuestion = "무슨 냄새가 났나요?",
        questEXP = 15,
    )

    @Test
    fun `난이도 큐와 결과 큐는 최근 10개만 유지된다`() {
        val user = User(
            difficultyQueue = List(10) { 1.0 },
            questResultsQueue = List(10) { 0 },
        )
        val completion = computeQuestCompletion(user.progression(), quest, "답", "2026.09.28 10:00:00")
        assertEquals(10, completion.newDifficultyQueue.size)
        assertEquals(3.0, completion.newDifficultyQueue.last(), 0.0)
        assertEquals(10, completion.newResultsQueue.size)
        assertEquals(1, completion.newResultsQueue.last())
    }

    @Test
    fun `Firestore용 Map과 로컬 기록이 같은 완료 시각을 쓴다`() {
        val completion = computeQuestCompletion(User().progression(), quest, "답", "2026.09.28 10:00:00")
        val map = completion.toPrevQuestMap()
        assertEquals(completion.prevQuest.doneDate, map["doneDate"])
        assertEquals("공원 벤치에 앉기", map["questName"])
        assertEquals("답", map["confirmAnswer"])
        assertEquals(setOf(
            "questName", "questEXP", "difficulty", "confirmQuestion", "confirmAnswer", "doneDate"
        ), map.keys)
    }
}
