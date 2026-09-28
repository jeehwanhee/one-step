package com.jeepark.onestep.data.model

import java.time.LocalDate
import java.time.temporal.ChronoUnit

// 발자취 "통계" 탭의 값 계산 (순수 함수, Android 의존 없음).
// 날짜는 LocalDate로만 다뤄서 서머타임·시간대 전환에도 "하루"가 항상 1일이다.

/** 통계 탭에 보여줄 값. 날짜를 읽을 수 없는 기록은 연속일·시작일 계산에서만 빠진다. */
data class QuestStats(
    val completedCount: Int,
    val totalExp: Int,
    val avgDifficulty: Double,
    val currentStreakDays: Int,
    val maxStreakDays: Int,
    val startDate: LocalDate?,
    val daysSinceStart: Int,
)

fun computeQuestStats(quests: List<PrevQuest>, today: LocalDate): QuestStats {
    val days = quests.mapNotNull { it.doneDay() }.toSet()
    val start = days.minOrNull()
    return QuestStats(
        completedCount    = quests.size,
        totalExp          = quests.sumOf { it.questEXP },
        avgDifficulty     = if (quests.isEmpty()) 0.0 else quests.map { it.difficulty }.average(),
        currentStreakDays = currentStreakDays(days, today),
        maxStreakDays     = maxStreakDays(days),
        startDate         = start,
        daysSinceStart    = start?.let { daysSince(it, today) } ?: 0,
    )
}

/** 오늘 또는 어제까지 이어진 연속 활동일 수. 어제도 오늘도 활동이 없으면 0. */
fun currentStreakDays(days: Set<LocalDate>, today: LocalDate): Int {
    var cursor = when {
        today in days                -> today
        today.minusDays(1) in days   -> today.minusDays(1)
        else                         -> return 0
    }
    var streak = 0
    while (cursor in days) {
        streak++
        cursor = cursor.minusDays(1)
    }
    return streak
}

/** 지금까지의 가장 긴 연속 활동일 수. 기록이 없으면 0. */
fun maxStreakDays(days: Collection<LocalDate>): Int {
    val sorted = days.toSortedSet().toList()
    if (sorted.isEmpty()) return 0

    var longest = 1
    var current = 1
    for (i in 1 until sorted.size) {
        current = if (sorted[i - 1].plusDays(1) == sorted[i]) current + 1 else 1
        if (current > longest) longest = current
    }
    return longest
}

/** [start]부터 [today]까지 지난 날수. 시작일이 미래면 0. */
fun daysSince(start: LocalDate, today: LocalDate): Int =
    ChronoUnit.DAYS.between(start, today).toInt().coerceAtLeast(0)
