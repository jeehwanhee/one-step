package com.jeepark.onestep.data.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
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
    fun `활동 기록이 없으면 현재 연속일은 0이다`() {
        assertEquals(0, currentStreakDays(emptySet(), today))
    }

    @Test
    fun `오늘만 활동했으면 1이다`() {
        assertEquals(1, currentStreakDays(setOf(day(0)), today))
    }

    @Test
    fun `어제까지만 활동했어도 연속일이 이어진다`() {
        assertEquals(1, currentStreakDays(setOf(day(-1)), today))
        assertEquals(3, currentStreakDays(setOf(day(-1), day(-2), day(-3)), today))
    }

    @Test
    fun `그저께가 마지막 활동이면 연속이 끊겨 0이다`() {
        assertEquals(0, currentStreakDays(setOf(day(-2), day(-3)), today))
    }

    @Test
    fun `오늘부터 이어진 날만 센다`() {
        val days = setOf(day(0), day(-1), day(-2), day(-4), day(-5)) // 3일 전 공백

        assertEquals(3, currentStreakDays(days, today))
    }

    @Test
    fun `미래 날짜만 있으면 0이다`() {
        assertEquals(0, currentStreakDays(setOf(day(1), day(2)), today))
    }

    @Test
    fun `월과 연도 경계를 넘어도 이어진다`() {
        val newYear = LocalDate.of(2027, 1, 1)
        val days = setOf(LocalDate.of(2026, 12, 30), LocalDate.of(2026, 12, 31), newYear)

        assertEquals(3, currentStreakDays(days, newYear))
    }

    // ===== maxStreakDays =====

    @Test
    fun `활동 기록이 없으면 최장 연속일은 0이다`() {
        assertEquals(0, maxStreakDays(emptyList()))
    }

    @Test
    fun `하루만 활동했으면 최장 연속일은 1이다`() {
        assertEquals(1, maxStreakDays(listOf(day(-10))))
    }

    @Test
    fun `여러 구간 중 가장 긴 구간을 반환한다`() {
        val days = listOf(day(-20), day(-19), day(-10), day(-9), day(-8), day(-7), day(-1))

        assertEquals(4, maxStreakDays(days))
    }

    @Test
    fun `입력 순서와 중복에 상관없이 계산한다`() {
        val days = listOf(day(-1), day(-3), day(-2), day(-2), day(-3))

        assertEquals(3, maxStreakDays(days))
    }

    @Test
    fun `윤년 2월 29일과 평년 경계를 하루로 센다`() {
        val leap = listOf(LocalDate.of(2028, 2, 28), LocalDate.of(2028, 2, 29), LocalDate.of(2028, 3, 1))
        val common = listOf(LocalDate.of(2027, 2, 28), LocalDate.of(2027, 3, 1))

        assertEquals(3, maxStreakDays(leap))
        assertEquals(2, maxStreakDays(common))
    }

    @Test
    fun `서머타임이 바뀌는 날을 걸쳐도 하루씩 이어진 것으로 센다`() {
        // 미국 서머타임 시작일(2026-03-08)은 23시간짜리 날 — 24시간 밀리초 계산이면 끊겼을 구간
        val days = listOf(LocalDate.of(2026, 3, 7), LocalDate.of(2026, 3, 8), LocalDate.of(2026, 3, 9))

        assertEquals(3, maxStreakDays(days))
    }

    // ===== daysSince =====

    @Test
    fun `시작일부터 오늘까지 지난 날수를 센다`() {
        assertEquals(0, daysSince(today, today))
        assertEquals(10, daysSince(day(-10), today))
    }

    @Test
    fun `시작일이 미래면 0이다`() {
        assertEquals(0, daysSince(day(3), today))
    }

    // ===== computeQuestStats =====

    @Test
    fun `기록이 없으면 모든 통계가 비어 있다`() {
        val stats = computeQuestStats(emptyList(), today)

        assertEquals(QuestStats(0, 0, 0.0, 0, 0, null, 0), stats)
    }

    @Test
    fun `완료 개수 경험치 평균 난이도를 합산한다`() {
        val quests = listOf(
            quest(day(0), exp = 10, difficulty = 2),
            quest(day(-1), exp = 20, difficulty = 4),
            quest(day(-5), exp = 30, difficulty = 3),
        )

        val stats = computeQuestStats(quests, today)

        assertEquals(3, stats.completedCount)
        assertEquals(60, stats.totalExp)
        assertEquals(3.0, stats.avgDifficulty, 1e-9)
    }

    @Test
    fun `시작일은 가장 이른 완료일이고 경과일과 연속일을 함께 계산한다`() {
        val quests = listOf(
            quest(day(0)), quest(day(0)), // 같은 날 두 개는 하루로 센다
            quest(day(-1)),
            quest(day(-9)),
        )

        val stats = computeQuestStats(quests, today)

        assertEquals(day(-9), stats.startDate)
        assertEquals(9, stats.daysSinceStart)
        assertEquals(2, stats.currentStreakDays)
        assertEquals(2, stats.maxStreakDays)
    }

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

    @Test
    fun `날짜를 읽을 수 있는 기록이 하나도 없으면 시작일은 null이다`() {
        val stats = computeQuestStats(listOf(PrevQuest(doneDate = "")), today)

        assertNull(stats.startDate)
        assertEquals(0, stats.daysSinceStart)
        assertEquals(0, stats.currentStreakDays)
        assertEquals(0, stats.maxStreakDays)
    }
}
