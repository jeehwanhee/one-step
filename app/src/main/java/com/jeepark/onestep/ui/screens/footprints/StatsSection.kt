package com.jeepark.onestep.ui.screens.footprints

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeepark.onestep.data.model.PrevQuest
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

// 진척도(CollectionScreen)에 있던 통계 섹션 — 발자취(FootprintsScreen)의 "통계" 탭에서 사용 예정.
// CollectionScreen에서 이곳으로 옮겨왔을 뿐, 계산 로직은 변경하지 않았다.

@Composable
fun StatsColumn(
    completedCount: Int,
    streakDays: Int,
    maxStreakDays: Int,
    startDate: String,
    dday: Int,
    totalExp: Int,
    avgDifficulty: Double,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        StatRow(label = "현재 연속 활동일",   value = "${streakDays}일")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color(0xFFEAE3D2))
        )
        StatRow(label = "최장 연속 활동일", value = "${maxStreakDays}일")
        StatRow(
            label = "활동 시작일",
            value = if (startDate.isNotEmpty()) "$startDate (D+$dday)" else "-"
        )

        StatRow(label = "총 획득 경험치", value = "${totalExp} XP")
        StatRow(label = "완료한 퀘스트", value = "${completedCount}개")
        StatRow(
            label = "평균 난이도",
            value = if (avgDifficulty > 0) String.format(Locale.getDefault(), "%.1f", avgDifficulty) else "-"
        )
    }
}

@Composable
private fun StatRow(label: String, value: String) {
    Row(
        modifier              = Modifier.fillMaxWidth(),
        verticalAlignment     = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text     = label,
            fontSize = 13.sp,
            color    = Color(0xFF6A6058)
        )
        Text(
            text       = value,
            fontSize   = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color      = Color(0xFF2A2A2A)
        )
    }
}

// ===== 통계 계산 =====

fun calcStreakDays(quests: List<PrevQuest>): Int {
    if (quests.isEmpty()) return 0
    val parser = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
    val days = quests.mapNotNull { q ->
        runCatching { parser.parse(q.doneDate.take(10))?.time }.getOrNull()
    }.map { ts ->
        val cal = Calendar.getInstance().apply {
            timeInMillis = ts
            set(Calendar.HOUR_OF_DAY, 0); set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0); set(Calendar.MILLISECOND, 0)
        }
        cal.timeInMillis
    }.toSortedSet()
    if (days.isEmpty()) return 0

    val today = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0); set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0); set(Calendar.MILLISECOND, 0)
    }
    val oneDay = 24L * 60 * 60 * 1000

    // 오늘 또는 어제부터 시작 가능
    var cursor = today.timeInMillis
    if (cursor !in days && (cursor - oneDay) !in days) return 0
    if (cursor !in days) cursor -= oneDay

    var streak = 0
    while (cursor in days) {
        streak++
        cursor -= oneDay
    }
    return streak
}

fun calcAvgDifficulty(quests: List<PrevQuest>): Double =
    if (quests.isEmpty()) 0.0
    else quests.map { it.difficulty }.average()

fun calcMaxStreakDays(quests: List<PrevQuest>): Int {
    if (quests.isEmpty()) return 0
    val parser = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
    val days = quests.mapNotNull { q ->
        runCatching { parser.parse(q.doneDate.take(10))?.time }.getOrNull()
    }.map { ts ->
        val cal = Calendar.getInstance().apply {
            timeInMillis = ts
            set(Calendar.HOUR_OF_DAY, 0); set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0); set(Calendar.MILLISECOND, 0)
        }
        cal.timeInMillis
    }.toSortedSet().toList()
    if (days.isEmpty()) return 0

    val oneDay = 24L * 60 * 60 * 1000
    var maxStreak = 1
    var current = 1
    for (i in 1 until days.size) {
        if (days[i] - days[i - 1] == oneDay) {
            current++
            if (current > maxStreak) maxStreak = current
        } else {
            current = 1
        }
    }
    return maxStreak
}

fun calcStartDate(quests: List<PrevQuest>): String {
    if (quests.isEmpty()) return ""
    val parser = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
    val firstTs = quests.mapNotNull { q ->
        runCatching { parser.parse(q.doneDate.take(10))?.time }.getOrNull()
    }.minOrNull() ?: return ""
    return parser.format(java.util.Date(firstTs))
}

fun calcDday(quests: List<PrevQuest>): Int {
    if (quests.isEmpty()) return 0
    val parser = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
    val firstTs = quests.mapNotNull { q ->
        runCatching { parser.parse(q.doneDate.take(10))?.time }.getOrNull()
    }.minOrNull() ?: return 0
    val today = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0); set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0); set(Calendar.MILLISECOND, 0)
    }.timeInMillis
    val oneDay = 24L * 60 * 60 * 1000
    return ((today - firstTs) / oneDay).toInt().coerceAtLeast(0)
}

fun calcTotalExp(quests: List<PrevQuest>): Int =
    quests.sumOf { it.questEXP }
