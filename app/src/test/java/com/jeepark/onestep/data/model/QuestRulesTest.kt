package com.jeepark.onestep.data.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class QuestRulesTest {

    private val today = "2026-09-28"

    // ===== 일일 제한 =====

    @Test
    fun `사용자가 없으면 일일 한도에 도달하지 않은 것으로 본다`() {
        assertFalse(hasReachedDailyLimit(null, today))
    }

    @Test
    fun `오늘 19개까지는 한도에 도달하지 않는다`() {
        val user = User(dailyQuestDate = today, dailyQuestCount = 19)
        assertFalse(hasReachedDailyLimit(user, today))
    }

    @Test
    fun `오늘 20개면 한도에 도달한다`() {
        val user = User(dailyQuestDate = today, dailyQuestCount = DAILY_QUEST_LIMIT)
        assertTrue(hasReachedDailyLimit(user, today))
    }

    @Test
    fun `날짜가 바뀌었으면 카운트가 많아도 한도에 도달하지 않는다`() {
        val user = User(dailyQuestDate = "2026-09-27", dailyQuestCount = 20)
        assertFalse(hasReachedDailyLimit(user, today))
    }

    // ===== 일일 카운트 증가 =====

    @Test
    fun `같은 날이면 카운트가 1 증가하고 날짜는 유지된다`() {
        val user = User(dailyQuestDate = today, dailyQuestCount = 3)
        val result = incrementedDailyCount(user, today)
        assertEquals(4, result.dailyQuestCount)
        assertEquals(today, result.dailyQuestDate)
    }

    @Test
    fun `날짜가 바뀌었으면 카운트가 1로 리셋되고 날짜가 갱신된다`() {
        val user = User(dailyQuestDate = "2026-09-27", dailyQuestCount = 9)
        val result = incrementedDailyCount(user, today)
        assertEquals(1, result.dailyQuestCount)
        assertEquals(today, result.dailyQuestDate)
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
    fun `완료 경험치가 문턱을 넘으면 티어가 오른다`() {
        val user = User(tier = 0, progress = 0)
        val completion = computeQuestCompletion(user, quest, "풀 냄새", "2026.09.28 10:00:00")
        assertEquals(1, completion.newTier)
        assertEquals(0, completion.newProgress)
        assertTrue(completion.didTierUp)
    }

    @Test
    fun `문턱에 못 미치면 진행도만 쌓이고 티어는 그대로다`() {
        val user = User(tier = 0, progress = 0)
        val small = quest.copy(questEXP = 5)
        val completion = computeQuestCompletion(user, small, "답", "2026.09.28 10:00:00")
        assertEquals(0, completion.newTier)
        assertEquals(5, completion.newProgress)
        assertFalse(completion.didTierUp)
    }

    @Test
    fun `난이도 큐와 결과 큐는 최근 10개만 유지된다`() {
        val user = User(
            difficultyQueue = List(10) { 1.0 },
            questResultsQueue = List(10) { 0 },
        )
        val completion = computeQuestCompletion(user, quest, "답", "2026.09.28 10:00:00")
        assertEquals(10, completion.newDifficultyQueue.size)
        assertEquals(3.0, completion.newDifficultyQueue.last(), 0.0)
        assertEquals(10, completion.newResultsQueue.size)
        assertEquals(1, completion.newResultsQueue.last())
    }

    @Test
    fun `완료 기록에는 퀘스트 정보와 답변과 완료 시각이 들어간다`() {
        val user = User()
        val completion = computeQuestCompletion(user, quest, "풀 냄새", "2026.09.28 10:00:00")
        val prev = completion.prevQuest
        assertEquals("공원 벤치에 앉기", prev.questName)
        assertEquals(15, prev.questEXP)
        assertEquals(3, prev.difficulty)
        assertEquals("무슨 냄새가 났나요?", prev.confirmQuestion)
        assertEquals("풀 냄새", prev.confirmAnswer)
        assertEquals("2026.09.28 10:00:00", prev.doneDate)
    }

    @Test
    fun `Firestore용 Map과 로컬 기록이 같은 완료 시각을 쓴다`() {
        val completion = computeQuestCompletion(User(), quest, "답", "2026.09.28 10:00:00")
        val map = completion.toPrevQuestMap()
        assertEquals(completion.prevQuest.doneDate, map["doneDate"])
        assertEquals("공원 벤치에 앉기", map["questName"])
        assertEquals("답", map["confirmAnswer"])
        assertEquals(setOf(
            "questName", "questEXP", "difficulty", "confirmQuestion", "confirmAnswer", "doneDate"
        ), map.keys)
    }

    @Test
    fun `완료 결과를 사용자에 적용하면 기록이 추가되고 카운트가 오른다`() {
        val existing = PrevQuest(questName = "이전 퀘스트")
        val user = User(nickname = "테스터", prevQuests = listOf(existing), isolatedCount = 4)
        val completion = computeQuestCompletion(user, quest, "답", "2026.09.28 10:00:00")

        val updated = completion.applyTo(user)

        assertEquals(2, updated.prevQuests.size)
        assertEquals(completion.prevQuest, updated.prevQuests.last())
        assertEquals(5, updated.isolatedCount)
        assertEquals(completion.newTier, updated.tier)
        assertEquals("테스터", updated.nickname) // 관련 없는 필드는 그대로
    }

    // ===== 포기 =====

    @Test
    fun `포기하면 결과 큐 끝에 실패(0)가 추가된다`() {
        val user = User(questResultsQueue = listOf(1, 1))
        assertEquals(listOf(1, 1, 0), giveUpResultsQueue(user))
    }

    @Test
    fun `포기 결과 큐도 최근 10개만 유지된다`() {
        val user = User(questResultsQueue = List(10) { 1 })
        val result = giveUpResultsQueue(user)
        assertEquals(10, result.size)
        assertEquals(0, result.last())
    }
}
