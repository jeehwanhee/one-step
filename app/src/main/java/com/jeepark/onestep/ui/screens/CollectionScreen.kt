package com.jeepark.onestep.ui.screens

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import kotlinx.coroutines.launch
import com.jeepark.onestep.data.model.PrevQuest
import com.jeepark.onestep.ui.viewmodels.CollectionViewModel
import com.jeepark.onestep.util.ANIMAL_NAMES
import com.jeepark.onestep.util.PixelAnimalRenderer

private val BG_COLOR = Color(0xFFFDF8F0)
private val CARD_BG  = Color(0xFFF5EFE4)
private val PRIMARY  = Color(0xFF5A9848)

private fun tierName(tier: Int): String = when (tier) {
    0    -> "새싹"
    1    -> "초보자"
    2    -> "탐험가"
    3    -> "모험가"
    4    -> "용사"
    5    -> "전사"
    6    -> "챔피언"
    else -> "전설"
}

private fun tierEmoji(tier: Int): String = when (tier) {
    0    -> "🌱"
    1    -> "🐾"
    2    -> "🌿"
    3    -> "⭐"
    4    -> "🌟"
    5    -> "💫"
    6    -> "👑"
    else -> "🏆"
}

private val TIER_BADGE_COLORS = listOf(
    Color(0xFF9EBF8A),
    Color(0xFF7DAF68),
    Color(0xFF5A9848),
    Color(0xFF3A8A30),
    Color(0xFF2A7A20),
    Color(0xFF1A6A10),
    Color(0xFFD4A820),
    Color(0xFFB8860B),
)

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

        LazyColumn(
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
                                if (dragOffset.value > screenWidthPx * 0.35f) {
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
                },
            contentPadding = PaddingValues(bottom = 32.dp)
        ) {
        // 헤더
        item {
            CollectionHeader(
                tier      = tier,
                progress  = progress,
                threshold = threshold,
                onBack    = onNavigateBack
            )
        }

        // 동물 도감 섹션 제목
        item {
            Text(
                text       = "동물 도감",
                fontSize   = 16.sp,
                fontWeight = FontWeight.Bold,
                color      = Color(0xFF3A3A3A),
                modifier   = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }

        // 동물 그리드 (4열 고정 높이)
        item {
            AnimalGrid(
                unlockedAnimals = unlockedAnimals,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )
        }

        // 완료 퀘스트 섹션 제목
        item {
            Row(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text       = "완료한 퀘스트",
                    fontSize   = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color      = Color(0xFF3A3A3A)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text     = "${completedQuests.size}개",
                    fontSize = 13.sp,
                    color    = PRIMARY
                )
            }
        }

        if (completedQuests.isEmpty()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text     = "아직 완료한 퀘스트가 없어요\n첫 퀘스트에 도전해 보세요!",
                        fontSize = 14.sp,
                        color    = Color(0xFFAAAAAA),
                        lineHeight = 22.sp
                    )
                }
            }
        } else {
            items(completedQuests) { quest ->
                CompletedQuestCard(
                    quest    = quest,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                )
            }
        }
        } // LazyColumn
    } // outer Box
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

    val badgeColor = TIER_BADGE_COLORS.getOrElse(tier) { PRIMARY }
    val isMaxTier  = tier >= 7

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(top = 8.dp, bottom = 20.dp)
    ) {
        // 뒤로가기 버튼 행
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
                text       = "도감",
                fontSize   = 18.sp,
                fontWeight = FontWeight.Bold,
                color      = Color(0xFF2A2A2A)
            )
        }

        // 티어 뱃지 + 정보
        Row(
            modifier          = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 티어 뱃지 (원형)
            Box(
                modifier          = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(badgeColor),
                contentAlignment  = Alignment.Center
            ) {
                Text(tierEmoji(tier), fontSize = 26.sp)
            }

            Spacer(Modifier.width(16.dp))

            Column {
                Text(
                    text       = "티어 ${tier} · ${tierName(tier)}",
                    fontSize   = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color      = Color(0xFF2A2A2A)
                )
                Spacer(Modifier.height(4.dp))
                if (isMaxTier) {
                    Text(
                        text     = "최고 티어 달성!",
                        fontSize = 13.sp,
                        color    = Color(0xFFD4A820)
                    )
                } else {
                    Text(
                        text     = "다음 티어까지 ${threshold - progress} XP",
                        fontSize = 13.sp,
                        color    = Color(0xFF888888)
                    )
                }
            }
        }

        // XP 진행 바
        if (!isMaxTier) {
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Row(
                    modifier          = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "$progress XP", fontSize = 12.sp, color = PRIMARY)
                    Text(text = "$threshold XP", fontSize = 12.sp, color = Color(0xFFAAAAAA))
                }
                Spacer(Modifier.height(4.dp))
                LinearProgressIndicator(
                    progress          = { animatedFraction },
                    modifier          = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
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
    val allAnimals = ANIMAL_NAMES.indices.toList() // 0..6

    LazyVerticalGrid(
        columns            = GridCells.Fixed(4),
        modifier           = modifier.height(((allAnimals.size / 4 + 1) * 100).dp),
        contentPadding     = PaddingValues(4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
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
        modifier = Modifier
            .aspectRatio(1f),
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
                modifier          = Modifier.size(40.dp),
                contentAlignment  = Alignment.Center
            ) {
                if (unlocked) {
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        with(PixelAnimalRenderer) {
                            drawAnimalInBox(index, Offset.Zero, size.width)
                        }
                    }
                } else {
                    Text("🔒", fontSize = 24.sp)
                }
            }
            Spacer(Modifier.height(4.dp))
            Text(
                text     = if (unlocked) ANIMAL_NAMES[index] else "???",
                fontSize = 11.sp,
                color    = if (unlocked) Color(0xFF4A4A4A) else Color(0xFFAAAAAA)
            )
        }
    }
}

// ===== 완료 퀘스트 카드 =====

@Composable
private fun CompletedQuestCard(quest: PrevQuest, modifier: Modifier = Modifier) {
    val levelColor = when (quest.difficulty) {
        1    -> Color(0xFF78C060)
        2    -> Color(0xFF60A8E0)
        3    -> Color(0xFFE0A030)
        4    -> Color(0xFFE06060)
        else -> Color(0xFF9060C8)
    }

    Card(
        modifier  = modifier.fillMaxWidth(),
        shape     = RoundedCornerShape(12.dp),
        colors    = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier          = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 레벨 뱃지
                Box(
                    modifier         = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(levelColor.copy(alpha = 0.15f))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text       = "Lv.${quest.difficulty}",
                        fontSize   = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color      = levelColor
                    )
                }
                Spacer(Modifier.weight(1f))
                Text(
                    text     = quest.doneDate.take(10),
                    fontSize = 11.sp,
                    color    = Color(0xFFAAAAAA)
                )
            }

            Spacer(Modifier.height(8.dp))
            Text(
                text       = quest.questName,
                fontSize   = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color      = Color(0xFF2A2A2A)
            )

            Spacer(Modifier.height(8.dp))
            Text(
                text     = "Q. ${quest.confirmQuestion}",
                fontSize = 12.sp,
                color    = Color(0xFF666666)
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text     = "A. ${quest.confirmAnswer}",
                fontSize = 12.sp,
                color    = Color(0xFF444444)
            )

            Spacer(Modifier.height(10.dp))
            Row(
                modifier          = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // difficulty 별점
                repeat(5) { i ->
                    Text(
                        text     = "★",
                        fontSize = 12.sp,
                        color    = if (i < quest.difficulty) Color(0xFFF0C030) else Color(0xFFE0D8C8)
                    )
                }
                Spacer(Modifier.weight(1f))
                Text(
                    text       = "+${quest.questEXP} XP",
                    fontSize   = 12.sp,
                    color      = PRIMARY,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CollectionScreenPreview() {
    CollectionScreen(onNavigateBack = {})
}
