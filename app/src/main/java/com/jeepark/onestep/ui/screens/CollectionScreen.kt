package com.jeepark.onestep.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jeepark.onestep.data.model.EXPAMOUNT
import com.jeepark.onestep.ui.components.flatShadow
import com.jeepark.onestep.ui.screens.collection.AnimalProfileCard
import com.jeepark.onestep.ui.theme.AmberText
import com.jeepark.onestep.ui.theme.CreamBackground
import com.jeepark.onestep.ui.theme.CreamSurface
import com.jeepark.onestep.ui.theme.HeadingText
import com.jeepark.onestep.ui.theme.MutedText
import com.jeepark.onestep.ui.theme.PrimaryGreen
import com.jeepark.onestep.ui.theme.SecondaryBackground
import com.jeepark.onestep.ui.viewmodels.CollectionViewModel
import com.jeepark.onestep.util.ANIMAL_NAMES
import com.jeepark.onestep.util.PixelAnimalRenderer
import com.jeepark.onestep.util.findAnimalUnlockDate

@Composable
fun CollectionScreen(
    modifier: Modifier = Modifier,
    vm: CollectionViewModel = viewModel(),
) {
    val user             by vm.user.collectAsState()
    val completedQuests  by vm.completedQuests.collectAsState()
    val unlockedAnimals  by vm.unlockedAnimals.collectAsState()
    val loadError        by vm.loadError.collectAsState()

    val tier     = (user?.tier ?: 0).coerceIn(0, 7)
    val progress = user?.progress ?: 0
    val threshold = if (tier < EXPAMOUNT.size) EXPAMOUNT[tier] else EXPAMOUNT.last()

    val snackbarHostState = remember { SnackbarHostState() }

    // 기본값: 가장 최근에 해금된 동물. 사용자가 직접 고르면 그 선택을 유지한다.
    var manuallySelected by remember { mutableStateOf<Int?>(null) }
    val selectedIndex = manuallySelected ?: unlockedAnimals.maxOrNull() ?: 0

    LaunchedEffect(loadError) {
        if (loadError != null) {
            val result = snackbarHostState.showSnackbar(
                message     = loadError ?: "정보를 불러오지 못했어요",
                actionLabel = "다시 시도"
            )
            if (result == SnackbarResult.ActionPerformed) vm.loadUser()
        }
    }

    Box(modifier = modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(CreamBackground)
        ) {
            // 헤더는 고정 (스크롤 영향 없음)
            CollectionHeader(
                tier      = tier,
                progress  = progress,
                threshold = threshold
            )

            // 본문만 세로 스크롤
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text       = "해금된 동물들",
                    fontSize   = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color      = HeadingText,
                    modifier   = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
                )

                AnimalGrid(
                    unlockedAnimals = unlockedAnimals,
                    selectedIndex   = selectedIndex,
                    onSelect        = { manuallySelected = it },
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                )

                Spacer(Modifier.height(14.dp))

                AnimalProfileCard(
                    animalIndex  = selectedIndex,
                    metDateLabel = findAnimalUnlockDate(selectedIndex, completedQuests, user?.agreedAt) ?: "-",
                    modifier     = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(Modifier.height(24.dp))   // 하단 여백
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp)
        ) { data ->
            Snackbar(
                snackbarData = data,
                containerColor = PrimaryGreen,
                contentColor = Color.White
            )
        }
    }
}

// ===== 헤더 =====

@Composable
private fun CollectionHeader(
    tier: Int,
    progress: Int,
    threshold: Int
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
            .background(CreamSurface)
            .padding(bottom = 14.dp)
    ) {
        Row(
            modifier          = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp, top = 32.dp, bottom = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text       = "진척도",
                fontSize   = 20.sp,
                fontWeight = FontWeight.Bold,
                color      = HeadingText
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
                color      = HeadingText
            )
            if (isMaxTier) {
                Text(
                    text     = "   •   최고 티어 달성!",
                    fontSize = 12.sp,
                    color    = AmberText
                )
            } else {
                Text(
                    text     = "   •   다음 티어까지 ${threshold - progress} XP",
                    fontSize = 12.sp,
                    color    = MutedText
                )
            }
        }

        if (!isMaxTier) {
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Row(
                    modifier          = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "$progress XP", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = AmberText)
                    Text(text = "$threshold XP", fontSize = 11.sp, color = MutedText)
                }
                Spacer(Modifier.height(3.dp))
                LinearProgressIndicator(
                    progress          = { animatedFraction },
                    modifier          = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    color             = PrimaryGreen,
                    trackColor        = SecondaryBackground,
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
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
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
            AnimalCard(
                index    = index,
                unlocked = unlocked,
                selected = unlocked && index == selectedIndex,
                onClick  = { onSelect(index) }
            )
        }
    }
}

@Composable
private fun AnimalCard(index: Int, unlocked: Boolean, selected: Boolean, onClick: () -> Unit) {
    val shape = RoundedCornerShape(14.dp)
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .let { if (unlocked) it.flatShadow(shape = shape, color = SecondaryBackground, offset = 2.dp) else it }
            .clip(shape)
            .background(if (unlocked) CreamSurface else SecondaryBackground.copy(alpha = 0.5f))
            .let { if (selected) it.border(2.dp, PrimaryGreen, shape) else it }
            .clickable(enabled = unlocked, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Column(
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
                        tint = MutedText,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            Spacer(Modifier.height(2.dp))
            Text(
                text     = if (unlocked) ANIMAL_NAMES[index] else "???",
                fontSize = 10.sp,
                color    = if (unlocked) HeadingText else MutedText
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CollectionScreenPreview() {
    CollectionScreen()
}
