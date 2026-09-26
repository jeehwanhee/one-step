package com.jeepark.onestep.ui.screens.footprints

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeepark.onestep.data.model.PrevQuest
import com.jeepark.onestep.ui.components.FlatCard
import com.jeepark.onestep.ui.theme.HeadingText
import com.jeepark.onestep.ui.theme.MutedText
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

private val LEVEL_0 = Color(0xFFEDE4CE)
private val LEVEL_1 = Color(0xFFB7E2A6)
private val LEVEL_2 = Color(0xFF5DA84C)
private val LEVEL_3 = Color(0xFF3F7A36)

private val DONE_DATE_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
private val WEEKDAY_LABELS = listOf("일", "월", "화", "수", "목", "금", "토")

private fun levelColorFor(count: Int): Color = when {
    count <= 0 -> LEVEL_0
    count == 1 -> LEVEL_1
    count <= 3 -> LEVEL_2
    else       -> LEVEL_3
}

private fun textColorFor(count: Int): Color = if (count >= 4) Color.White else HeadingText

private fun PrevQuest.doneLocalDateOrNull(): LocalDate? =
    runCatching { LocalDate.parse(doneDate.take(10), DONE_DATE_FORMAT) }.getOrNull()

/** "퀘스트 완료 기록" 캘린더 히트맵: 월별로 하루당 완료 개수를 4단계 색으로 표시한다. */
@Composable
fun QuestActivityCalendar(
    completedQuests: List<PrevQuest>,
    modifier: Modifier = Modifier
) {
    var displayedMonth by remember { mutableStateOf(YearMonth.now()) }

    val countsByDate = remember(completedQuests, displayedMonth) {
        completedQuests
            .mapNotNull { it.doneLocalDateOrNull() }
            .filter { YearMonth.from(it) == displayedMonth }
            .groupingBy { it }
            .eachCount()
    }

    FlatCard(modifier = modifier.fillMaxWidth(), cornerRadius = 20.dp) {
        Column(modifier = Modifier.padding(18.dp)) {
            Text(
                text = "퀘스트 완료 기록",
                fontSize = 13.5.sp,
                fontWeight = FontWeight.Bold,
                color = HeadingText,
                modifier = Modifier.padding(bottom = 10.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { displayedMonth = displayedMonth.minusMonths(1) },
                    modifier = Modifier.size(26.dp)
                ) {
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "이전 달", tint = MutedText)
                }
                Spacer(Modifier.width(12.dp))
                Text(
                    text = "${displayedMonth.year}년 ${displayedMonth.monthValue}월",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = HeadingText
                )
                Spacer(Modifier.width(12.dp))
                IconButton(
                    onClick = { displayedMonth = displayedMonth.plusMonths(1) },
                    modifier = Modifier.size(26.dp)
                ) {
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "다음 달", tint = MutedText)
                }
            }

            Row(modifier = Modifier.fillMaxWidth().padding(bottom = 6.dp)) {
                WEEKDAY_LABELS.forEach { label ->
                    Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                        Text(text = label, fontSize = 10.5.sp, color = MutedText)
                    }
                }
            }

            val today = LocalDate.now()
            val firstDayOffset = displayedMonth.atDay(1).dayOfWeek.value % 7 // 일=0 ... 토=6
            val daysInMonth = displayedMonth.lengthOfMonth()
            val totalCells = firstDayOffset + daysInMonth
            val rows = (totalCells + 6) / 7

            for (row in 0 until rows) {
                Row(modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp)) {
                    for (col in 0 until 7) {
                        val cellIndex = row * 7 + col
                        val day = cellIndex - firstDayOffset + 1
                        Box(modifier = Modifier.weight(1f).aspectRatio(1f).padding(1.5.dp)) {
                            if (day in 1..daysInMonth) {
                                val date = displayedMonth.atDay(day)
                                val isFuture = date.isAfter(today)
                                val count = countsByDate[date] ?: 0
                                DayCell(
                                    day = day,
                                    count = count,
                                    isFuture = isFuture,
                                    isToday = date == today
                                )
                            }
                        }
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "적음", fontSize = 10.sp, color = MutedText)
                Spacer(Modifier.width(4.dp))
                listOf(LEVEL_0, LEVEL_1, LEVEL_2, LEVEL_3).forEach { color ->
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 2.dp)
                            .size(10.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(color)
                    )
                }
                Spacer(Modifier.width(4.dp))
                Text(text = "많음", fontSize = 10.sp, color = MutedText)
            }
        }
    }
}

@Composable
private fun DayCell(day: Int, count: Int, isFuture: Boolean, isToday: Boolean) {
    val shape = RoundedCornerShape(6.dp)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(shape)
            .background(if (isFuture) Color.Transparent else levelColorFor(count))
            .let { if (isToday) it.border(1.5.dp, HeadingText, shape) else it },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day.toString(),
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            color = if (isFuture) MutedText.copy(alpha = 0.5f) else textColorFor(count)
        )
    }
}
