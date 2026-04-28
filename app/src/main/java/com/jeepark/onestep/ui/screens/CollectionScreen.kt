package com.jeepark.onestep.ui.screens

import android.R.attr.label
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jeepark.onestep.data.model.EXPAMOUNT
import com.jeepark.onestep.data.model.PrevQuest
import com.jeepark.onestep.ui.viewmodels.CollectionViewModel
import com.jeepark.onestep.util.ANIMAL_NAMES
import com.jeepark.onestep.util.PixelAnimalRenderer
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

private val BG_COLOR = Color(0xFFFDF8F0)
private val PRIMARY  = Color(0xFF5A9848)


@Composable
fun CollectionScreen(
    modifier: Modifier = Modifier,
    vm: CollectionViewModel = viewModel(),
    onNavigateBack: () -> Unit
) {
    val user             by vm.user.collectAsState()
    val completedQuests  by vm.completedQuests.collectAsState()
    val unlockedAnimals  by vm.unlockedAnimals.collectAsState()

    val tier     = (user?.tier ?: 0).coerceIn(0, 7)
    val progress = user?.progress ?: 0
    val threshold = if (tier < EXPAMOUNT.size) EXPAMOUNT[tier] else EXPAMOUNT.last()

    val scope         = rememberCoroutineScope()
    val dragOffset    = remember { Animatable(0f) }
    val screenWidthPx = with(LocalDensity.current) {
        LocalConfiguration.current.screenWidthDp.dp.toPx()
    }

    Box(modifier = modifier.fillMaxSize()) {

        // 스와이프 미리보기: MainScreen 공원 배경
        ParkBackground(
            tier = tier,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { translationX = -screenWidthPx + dragOffset.value }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { translationX = dragOffset.value }
                .background(BG_COLOR)
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onDragStart  = { scope.launch { dragOffset.snapTo(0f) } },
                        onDragCancel = { scope.launch { dragOffset.animateTo(0f, spring()) } },
                        onDragEnd    = {
                            scope.launch {
                                if (dragOffset.value > screenWidthPx * 0.18f) {
                                    dragOffset.animateTo(screenWidthPx, tween(200))
                                    onNavigateBack()
                                } else {
                                    dragOffset.animateTo(0f, spring())
                                }
                            }
                        },
                        onHorizontalDrag = { change, dragAmount ->
                            change.consume()
                            scope.launch {
                                dragOffset.snapTo((dragOffset.value + dragAmount).coerceAtLeast(0f))
                            }
                        }
                    )
                }
        ) {
            CollectionHeader(
                tier      = tier,
                progress  = progress,
                threshold = threshold,
                onBack    = onNavigateBack
            )

            Text(
                text       = "해금된 동물들",
                fontSize   = 14.sp,
                fontWeight = FontWeight.Bold,
                color      = Color(0xFF3A3A3A),
                modifier   = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
            )

            AnimalGrid(
                unlockedAnimals = unlockedAnimals,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            RecoveryGraph(
                history  = user?.isolatedHistory ?: emptyList(),
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(Modifier.height(10.dp))

            StatsColumn(
                completedCount = completedQuests.size,
                streakDays     = calcStreakDays(completedQuests),
                maxStreakDays  = calcMaxStreakDays(completedQuests),
                startDate      = calcStartDate(completedQuests),
                dday           = calcDday(completedQuests),
                totalExp       = calcTotalExp(completedQuests),
                avgDifficulty  = calcAvgDifficulty(completedQuests),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

// ===== 헤더 =====

@Composable
private fun CollectionHeader(
    tier: Int,
    progress: Int,
    threshold: Int,
    onBack: () -> Unit
) {
    val targetFraction = if (threshold > 0) progress.toFloat() / threshold else 1f
    var animFraction by remember { mutableFloatStateOf(0f) }
    val animatedFraction by animateFloatAsState(
        targetValue  = animFraction,
        animationSpec = tween(durationMillis = 800),
        label        = "progress"
    )
    LaunchedEffect(targetFraction) { animFraction = targetFraction }

    val isMaxTier  = tier >= 7

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(top = 8.dp, bottom = 14.dp)
    ) {
        Row(
            modifier          = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector        = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "뒤로가기",
                    tint               = Color(0xFF4A4A4A)
                )
            }
            Text(
                text       = "진척도",
                fontSize   = 18.sp,
                fontWeight = FontWeight.Bold,
                color      = Color(0xFF2A2A2A)
            )
        }

        Row(
            modifier              = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 6.dp),
            verticalAlignment     = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text       = "티어 ${tier}",
                fontSize   = 16.sp,
                fontWeight = FontWeight.Bold,
                color      = Color(0xFF2A2A2A)
            )
            if (isMaxTier) {
                Text(
                    text     = "   •   최고 티어 달성!",
                    fontSize = 12.sp,
                    color    = Color(0xFFD4A820)
                )
            } else {
                Text(
                    text     = "   •   다음 티어까지 ${threshold - progress} XP",
                    fontSize = 12.sp,
                    color    = Color(0xFF888888)
                )
            }
        }

        if (!isMaxTier) {
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Row(
                    modifier          = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "$progress XP", fontSize = 11.sp, color = PRIMARY)
                    Text(text = "$threshold XP", fontSize = 11.sp, color = Color(0xFFAAAAAA))
                }
                Spacer(Modifier.height(3.dp))
                LinearProgressIndicator(
                    progress          = { animatedFraction },
                    modifier          = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    color             = PRIMARY,
                    trackColor        = Color(0xFFE0E0E0),
                    strokeCap         = StrokeCap.Round
                )
            }
        }
    }
}

// ===== 동물 그리드 =====

@Composable
private fun AnimalGrid(
    unlockedAnimals: List<Int>,
    modifier: Modifier = Modifier
) {
    val allAnimals = ANIMAL_NAMES.indices.toList()

    LazyVerticalGrid(
        columns            = GridCells.Fixed(4),
        modifier           = modifier.height((((allAnimals.size + 3) / 4) * 88).dp),
        contentPadding     = PaddingValues(4.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        userScrollEnabled  = false
    ) {
        items(allAnimals) { index ->
            val unlocked = index in unlockedAnimals
            AnimalCard(index = index, unlocked = unlocked)
        }
    }
}

@Composable
private fun AnimalCard(index: Int, unlocked: Boolean) {
    Card(
        modifier = Modifier.aspectRatio(1f),
        shape  = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (unlocked) Color.White else Color(0xFFEAEAEA)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = if (unlocked) 2.dp else 0.dp)
    ) {
        Column(
            modifier          = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier          = Modifier.size(36.dp),
                contentAlignment  = Alignment.Center
            ) {
                if (unlocked) {
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        with(PixelAnimalRenderer) {
                            drawAnimalInBox(index, Offset.Zero, size.width)
                        }
                    }
                } else {
                    Icon(
                        imageVector = Icons.Outlined.Lock,
                        contentDescription = "잠금",
                        tint = Color(0xFFAAAAAA),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            Spacer(Modifier.height(2.dp))
            Text(
                text     = if (unlocked) ANIMAL_NAMES[index] else "???",
                fontSize = 10.sp,
                color    = if (unlocked) Color(0xFF4A4A4A) else Color(0xFFAAAAAA)
            )
        }
    }
}

// ===== 통계 (Column 3개 항목) =====

@Composable
private fun StatsColumn(
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

private fun calcStreakDays(quests: List<PrevQuest>): Int {
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

private fun calcAvgDifficulty(quests: List<PrevQuest>): Double =
    if (quests.isEmpty()) 0.0
    else quests.map { it.difficulty }.average()

private fun calcMaxStreakDays(quests: List<PrevQuest>): Int {
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

private fun calcStartDate(quests: List<PrevQuest>): String {
    if (quests.isEmpty()) return ""
    val parser = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
    val firstTs = quests.mapNotNull { q ->
        runCatching { parser.parse(q.doneDate.take(10))?.time }.getOrNull()
    }.minOrNull() ?: return ""
    return parser.format(java.util.Date(firstTs))
}

private fun calcDday(quests: List<PrevQuest>): Int {
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

private fun calcTotalExp(quests: List<PrevQuest>): Int =
    quests.sumOf { it.questEXP }

@Preview(showBackground = true)
@Composable
private fun CollectionScreenPreview() {
    CollectionScreen(onNavigateBack = {})
}
