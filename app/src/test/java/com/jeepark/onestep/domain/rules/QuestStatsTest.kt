package com.jeepark.onestep.domain.rules

import com.jeepark.onestep.domain.model.PrevQuest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate

class QuestStatsTest {

    private val today = LocalDate.of(2026, 9, 28)

    private fun day(offsetFromToday: Long): LocalDate = today.plusDays(offsetFromToday)

    private fun quest(date: LocalDate, exp: Int = 10, difficulty: Int = 3) = PrevQuest(
        questEXP = exp,
        difficulty = difficulty,
        doneDate = "${QuestDate.formatDay(date)} 12:00:00",
    )

    // ===== currentStreakDays =====

    @Test
    fun `어제까지만 활동했어도 연속일이 이어진다`() {
        assertEquals(1, currentStreakDays(setOf(day(-1)), today))
        assertEquals(3, currentStreakDays(setOf(day(-1), day(-2), day(-3)), today))
    }

    @Test
    fun `그저께가 마지막 활동이면 연속이 끊겨 0이다`() {
        assertEquals(0, currentStreakDays(setOf(day(-2), day(-3)), today))
    }

    // ===== maxStreakDays =====

    @Test
    fun `여러 구간 중 가장 긴 구간을 반환한다`() {
        val days = listOf(day(-20), day(-19), day(-10), day(-9), day(-8), day(-7), day(-1))

        assertEquals(4, maxStreakDays(days))
    }

    @Test
    fun `서머타임이 바뀌는 날을 걸쳐도 하루씩 이어진 것으로 센다`() {
        // 미국 서머타임 시작일(2026-03-08)은 23시간짜리 날 — 24시간 밀리초 계산이면 끊겼을 구간
        val days = listOf(LocalDate.of(2026, 3, 7), LocalDate.of(2026, 3, 8), LocalDate.of(2026, 3, 9))

        assertEquals(3, maxStreakDays(days))
    }

    // ===== computeQuestStats =====

    @Test
    fun `날짜를 읽을 수 없는 기록도 개수와 경험치에는 포함되고 연속일과 시작일에서만 빠진다`() {
        val broken = PrevQuest(questEXP = 50, difficulty = 5, doneDate = "날짜없음")
        val quests = listOf(quest(day(0), exp = 10, difficulty = 1), broken)

        val stats = computeQuestStats(quests, today)

        assertEquals(2, stats.completedCount)
        assertEquals(60, stats.totalExp)
        assertEquals(3.0, stats.avgDifficulty, 1e-9)
        assertEquals(1, stats.currentStreakDays)
        assertEquals(day(0), stats.startDate)
    }
}
