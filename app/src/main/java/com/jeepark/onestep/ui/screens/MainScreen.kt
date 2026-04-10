package com.jeepark.onestep.ui.screens

import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import com.jeepark.onestep.data.model.Quest
import com.jeepark.onestep.ui.viewmodels.MainViewModel
import kotlinx.coroutines.launch

// ===== 메인 화면 =====

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onNavigateToProgress: () -> Unit,
    onNavigateToSetting: () -> Unit,
    onNavigateToInitQuestion: () -> Unit = {},
) {
    val context = LocalContext.current
    val vm: MainViewModel = viewModel(
        factory = androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory(
            context.applicationContext as android.app.Application
        )
    )
    val user by vm.user.collectAsState()
    val questList by vm.questList.collectAsState()
    val isLoadingQuests by vm.isLoadingQuests.collectAsState()
    val activeQuest by vm.activeQuest.collectAsState()
    val tier = (user?.tier ?: 0).coerceIn(0, 7)
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    var showInputDialog   by remember { mutableStateOf(false) }
    var showSuggestDialog by remember { mutableStateOf(false) }
    var showVerifyDialog  by remember { mutableStateOf(false) }
    var showGiveUpDialog  by remember { mutableStateOf(false) }
    var selectedMood      by remember { mutableStateOf(-1) }
    var currentQuestIndex by remember { mutableStateOf(0) }
    var currentQuest      by remember { mutableStateOf<Quest?>(null) }
    var showTierUp        by remember { mutableStateOf(false) }
    val dragOffset        = remember { Animatable(0f) }
    val screenWidthPx     = with(LocalDensity.current) {
        LocalConfiguration.current.screenWidthDp.dp.toPx()
    }

    LaunchedEffect(showTierUp) {
        if (showTierUp) {
            snackbarHostState.showSnackbar("새 친구가 나타났어요!")
            showTierUp = false
        }
    }

    LaunchedEffect(user) {
        val count = user?.isolatedCount ?: 0
        if (count >= 10) {
            vm.resetIsolatedCount()
            onNavigateToInitQuestion()
        }
    }

    Box(modifier = modifier.fillMaxSize()) {

        // 스와이프 미리보기: CollectionScreen 배경색
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { translationX = screenWidthPx + dragOffset.value }
                .background(Color(0xFFFDF8F0))
        )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer { translationX = dragOffset.value }
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragStart  = { scope.launch { dragOffset.snapTo(0f) } },
                    onDragCancel = { scope.launch { dragOffset.animateTo(0f, spring()) } },
                    onDragEnd    = {
                        scope.launch {
                            if (dragOffset.value < -screenWidthPx * 0.35f) {
                                dragOffset.animateTo(-screenWidthPx, tween(200))
                                onNavigateToProgress()
                            } else {
                                dragOffset.animateTo(0f, spring())
                            }
                        }
                    },
                    onHorizontalDrag = { change, dragAmount ->
                        change.consume()
                        scope.launch {
                            dragOffset.snapTo((dragOffset.value + dragAmount).coerceAtMost(0f))
                        }
                    }
                )
            }
    ) {
        ParkBackground(tier = tier)

        // 활성 퀘스트가 없을 때만 퀘스트 버튼 표시
        if (activeQuest == null) {
            val limitReached = vm.isDailyLimitReached()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 14.dp, vertical = 24.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                QuestButton(limitReached = limitReached) { if (!limitReached) showInputDialog = true }
            }
        }

        // 활성 퀘스트 카드 (하단)
        activeQuest?.let { quest ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                ActiveQuestCard(
                    quest      = quest,
                    onGiveUp   = { showGiveUpDialog = true },
                    onComplete = { showVerifyDialog = true }
                )
            }
        }

        // 우상단 설정 버튼
        IconButton(
            onClick  = onNavigateToSetting,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 8.dp, end = 4.dp)
        ) {
            Icon(
                imageVector        = Icons.Default.Menu,
                contentDescription = "설정",
                tint               = Color.White,
                modifier           = Modifier.size(28.dp)
            )
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 110.dp)
        ) { data ->
            Snackbar(
                snackbarData = data,
                containerColor = Color(0xFF5A9848),
                contentColor = Color.White
            )
        }
    } // inner Box (draggable content)
    }  // outer Box

    // ---- 다이얼로그 ----
    if (showInputDialog) {
        QuestInputDialog(
            isLoading = isLoadingQuests,
            onDismiss = { if (!isLoadingQuests) showInputDialog = false },
            onSearch  = { mood ->
                selectedMood = mood
                vm.loadFilteredQuests(
                    mood    = mood + 1,
                    onReady = { quests ->
                        showInputDialog   = false
                        currentQuestIndex = 0
                        currentQuest      = quests.firstOrNull()
                        if (currentQuest != null) showSuggestDialog = true
                        else Toast.makeText(context, "퀘스트를 찾지 못했어요", Toast.LENGTH_SHORT).show()
                    },
                    onError  = { msg ->
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                    }
                )
            }
        )
    }

    currentQuest?.let { quest ->
        if (showSuggestDialog) {
            QuestSuggestDialog(
                quest     = quest,
                onDismiss = { showSuggestDialog = false },
                onSkip    = {
                    if (questList.isNotEmpty()) {
                        currentQuestIndex = (currentQuestIndex + 1) % questList.size
                        currentQuest = questList[currentQuestIndex]
                    }
                },
                onAccept  = {
                    showSuggestDialog = false
                    vm.startQuest(quest)
                }
            )
        }
    }

    activeQuest?.let { quest ->
        if (showVerifyDialog) {
            QuestVerifyDialog(
                quest     = quest,
                onDismiss = { showVerifyDialog = false },
                onSubmit  = { answer ->
                    vm.saveCompletedQuest(
                        quest    = quest,
                        answer   = answer,
                        onTierUp = { showTierUp = true },
                        onDone    = {
                            showVerifyDialog = false
                            vm.clearActiveQuest()
                        }
                    )
                }
            )
        }
        if (showGiveUpDialog) {
            GiveUpReasonDialog(
                onDismiss = { showGiveUpDialog = false },
                onSubmit  = { reason ->
                    vm.saveGiveUpQuest(quest, reason)
                    vm.clearActiveQuest()
                    showGiveUpDialog = false
                }
            )
        }
    }
}

// ===== 하단 퀘스트 버튼 =====

@Composable
private fun QuestButton(limitReached: Boolean, onClick: () -> Unit) {
    Button(
        onClick  = onClick,
        enabled  = !limitReached,
        modifier = Modifier.fillMaxWidth(),
        shape    = RoundedCornerShape(18.dp),
        colors   = ButtonDefaults.buttonColors(
            containerColor         = Color(0xFF5A9848),
            disabledContainerColor = Color(0xFFAAAAAA)
        ),
        border         = BorderStroke(1.5.dp, Color.White.copy(alpha = 0.22f)),
        contentPadding = PaddingValues(vertical = 20.dp, horizontal = 20.dp)
    ) {
        Text(
            text       = if (limitReached) "오늘은 여기까지, 내일 시도해 주세요" else "새 퀘스트 받기",
            fontSize   = 15.sp,
            color      = Color.White,
            fontWeight = FontWeight.Medium
        )
    }
}

// ===== 퀘스트 조건 다이얼로그 =====

@Composable
private fun QuestInputDialog(
    isLoading: Boolean,
    onDismiss: () -> Unit,
    onSearch: (mood: Int) -> Unit
) {
    var selectedMood  by remember { mutableStateOf(-1) }
    var visible       by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }
    val scale by animateFloatAsState(if (visible) 1f else 0.94f, animationSpec = tween(200), label = "scale")
    val alpha by animateFloatAsState(if (visible) 1f else 0f,    animationSpec = tween(200), label = "alpha")

    val moods = listOf("매우 나쁨", "나쁨", "보통", "좋음", "매우 좋음")

    Dialog(onDismissRequest = onDismiss, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        Card(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .graphicsLayer { scaleX = scale; scaleY = scale; this.alpha = alpha },
            shape     = RoundedCornerShape(22.dp),
            colors    = CardDefaults.cardColors(containerColor = Color(0xFFFAF7F1)),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier  = Modifier.padding(horizontal = 18.dp, vertical = 22.dp),
                verticalArrangement = Arrangement.spacedBy(0.dp)
            ) {
                Text(
                    "퀘스트 조건 설정",
                    fontSize   = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color      = Color(0xFF3A3228),
                    modifier   = Modifier.padding(bottom = 18.dp)
                )

                // 기분 섹션
                Text("지금 기분", fontSize = 10.sp, color = Color(0xFF8A7A60), letterSpacing = 1.2.sp,
                    modifier = Modifier.padding(bottom = 7.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    moods.forEachIndexed { i, mood ->
                        MoodChip(mood, selectedMood == i) { if (!isLoading) selectedMood = i }
                    }
                }

                // 퀘스트 찾기 버튼
                Button(
                    onClick  = { if (selectedMood >= 0 && !isLoading) onSearch(selectedMood) },
                    modifier = Modifier.fillMaxWidth(),
                    enabled  = selectedMood >= 0 && !isLoading,
                    shape    = RoundedCornerShape(14.dp),
                    colors   = ButtonDefaults.buttonColors(
                        containerColor         = Color(0xFF6A9858),
                        disabledContainerColor = Color(0xFFB8B0A0)
                    ),
                    contentPadding = PaddingValues(13.dp)
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier    = Modifier.size(18.dp),
                            color       = Color.White,
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text(
                            "퀘스트 찾기",
                            fontSize   = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color      = if (selectedMood >= 0) Color.White else Color(0xFFECE8E0)
                        )
                    }
                }

                // 취소
                Text(
                    "취소",
                    modifier  = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .clickable(
                            indication        = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) { if (!isLoading) onDismiss() },
                    textAlign = TextAlign.Center,
                    fontSize  = 12.sp,
                    color     = Color(0xFFB0A890)
                )
            }
        }
    }
}


@Composable
private fun MoodChip(text: String, selected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(if (selected) Color(0xFFD4EAD0) else Color(0xFFF0ECE0))
            .border(1.dp, if (selected) Color(0xFF7AB870) else Color(0xFFD4CDB8), RoundedCornerShape(10.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 7.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text,
            color      = if (selected) Color(0xFF2D5A2D) else Color(0xFF7A6E60),
            fontSize   = 10.sp,
            fontWeight = if (selected) FontWeight.Medium else FontWeight.Normal,
            maxLines   = 1
        )
    }
}

// ===== 퀘스트 추천 다이얼로그 =====

@Composable
private fun QuestSuggestDialog(
    quest: Quest,
    onDismiss: () -> Unit,
    onSkip: () -> Unit,
    onAccept: () -> Unit
) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }
    val scale by animateFloatAsState(if (visible) 1f else 0.94f, animationSpec = tween(200), label = "scale")
    val alpha by animateFloatAsState(if (visible) 1f else 0f,    animationSpec = tween(200), label = "alpha")

    Dialog(onDismissRequest = onDismiss, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        Card(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .graphicsLayer { scaleX = scale; scaleY = scale; this.alpha = alpha },
            shape     = RoundedCornerShape(22.dp),
            colors    = CardDefaults.cardColors(containerColor = Color(0xFFFAF7F1)),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(quest.questName, fontSize = 17.sp, fontWeight = FontWeight.Medium, color = Color(0xFF3A3228), lineHeight = 24.sp,
                    modifier = Modifier.padding(bottom = 12.dp))

                // 난이도 + 경험치 행
                Row(
                    modifier          = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    StarRating(quest.difficulty)
                    Spacer(modifier = Modifier.weight(1f))
                    Text("+${quest.questEXP} XP", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color(0xFF5A7A30))
                }

                // 버튼 행
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick  = onSkip,
                        modifier = Modifier.weight(1f),
                        shape    = RoundedCornerShape(12.dp),
                        border   = BorderStroke(1.dp, Color(0xFFD4CDB8)),
                        colors   = ButtonDefaults.outlinedButtonColors(containerColor = Color(0xFFF0ECE0), contentColor = Color(0xFF6A6058))
                    ) { Text("넘기기", fontSize = 13.sp) }

                    Button(
                        onClick  = onAccept,
                        modifier = Modifier.weight(1f),
                        shape    = RoundedCornerShape(12.dp),
                        colors   = ButtonDefaults.buttonColors(containerColor = Color(0xFF6A9858))
                    ) { Text("수락하기", fontSize = 13.sp, color = Color.White) }
                }

                Text(
                    "취소",
                    modifier  = Modifier.fillMaxWidth().clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { onDismiss() },
                    textAlign = TextAlign.Center,
                    fontSize  = 12.sp,
                    color     = Color(0xFFB0A890)
                )
            }
        }
    }
}

// ===== 퀘스트 수행 다이얼로그 =====

@Composable
private fun ActiveQuestCard(
    quest: Quest,
    onGiveUp: () -> Unit,
    onComplete: () -> Unit
) {
    Card(
        modifier  = Modifier
            .padding(horizontal = 16.dp, vertical = 20.dp)
            .fillMaxWidth(),
        shape     = RoundedCornerShape(22.dp),
        colors    = CardDefaults.cardColors(containerColor = Color(0xFFFAF7F1)),
        elevation = CardDefaults.cardElevation(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(vertical = 28.dp, horizontal = 22.dp),
        ) {
            Text(
                quest.questName,
                fontSize   = 22.sp,
                fontWeight = FontWeight.Medium,
                color      = Color(0xFF3A3228),
                lineHeight = 32.sp,
                modifier   = Modifier.padding(bottom = 24.dp)
            )

            Row(
                modifier          = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                StarRating(quest.difficulty, starSize = 18.sp)
                Spacer(modifier = Modifier.weight(1f))
                Text("+${quest.questEXP} XP", fontSize = 15.sp, fontWeight = FontWeight.Medium, color = Color(0xFF5A7A30))
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick  = onGiveUp,
                    modifier = Modifier.weight(1f).defaultMinSize(minHeight = 52.dp),
                    shape    = RoundedCornerShape(12.dp),
                    border   = BorderStroke(1.dp, Color(0xFFD4CDB8)),
                    colors   = ButtonDefaults.outlinedButtonColors(containerColor = Color(0xFFF0ECE0), contentColor = Color(0xFF8A7068))
                ) { Text("포기", fontSize = 13.sp) }

                Button(
                    onClick  = onComplete,
                    modifier = Modifier.weight(2f).defaultMinSize(minHeight = 52.dp),
                    shape    = RoundedCornerShape(12.dp),
                    colors   = ButtonDefaults.buttonColors(containerColor = Color(0xFF6A9858))
                ) { Text("완료했어요", fontSize = 14.sp, color = Color.White) }
            }
        }
    }
}

// ===== 퀘스트 포기 사유 다이얼로그 =====

@Composable
private fun GiveUpReasonDialog(
    onDismiss: () -> Unit,
    onSubmit: (Int) -> Unit
) {
    val reasons = listOf(
        1 to "퀘스트가 어려워서",
        2 to "현재 퀘스트를 진행할 상황이 아니라서",
        3 to "퀘스트를 진행할 컨디션이 아니라서"
    )
    var selected by remember { mutableStateOf(-1) }

    Dialog(onDismissRequest = onDismiss, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        Card(
            modifier  = Modifier.padding(horizontal = 20.dp).fillMaxWidth(),
            shape     = RoundedCornerShape(22.dp),
            colors    = CardDefaults.cardColors(containerColor = Color(0xFFFAF7F1)),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("포기 사유를 선택해주세요", fontSize = 15.sp, fontWeight = FontWeight.Medium, color = Color(0xFF3A3228))

                reasons.forEach { (id, label) ->
                    val isSelected = selected == id
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (isSelected) Color(0xFFD4EAD0) else Color(0xFFF0ECE0))
                            .border(1.dp, if (isSelected) Color(0xFF7AB870) else Color(0xFFD4CDB8), RoundedCornerShape(12.dp))
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) { selected = id }
                            .padding(horizontal = 16.dp, vertical = 14.dp)
                    ) {
                        Text(
                            text      = label,
                            fontSize  = 13.sp,
                            color     = if (isSelected) Color(0xFF2D5A2D) else Color(0xFF5A5248),
                            fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal
                        )
                    }
                }

                Button(
                    onClick  = { if (selected >= 0) onSubmit(selected) },
                    modifier = Modifier.fillMaxWidth(),
                    enabled  = selected >= 0,
                    shape    = RoundedCornerShape(14.dp),
                    colors   = ButtonDefaults.buttonColors(
                        containerColor         = Color(0xFF6A9858),
                        disabledContainerColor = Color(0xFFB8B0A0)
                    )
                ) {
                    Text("포기하기", color = Color.White, fontSize = 14.sp)
                }

                Text(
                    "취소",
                    modifier  = Modifier.fillMaxWidth().clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { onDismiss() },
                    textAlign = TextAlign.Center,
                    fontSize  = 12.sp,
                    color     = Color(0xFFB0A890)
                )
            }
        }
    }
}

// ===== 퀘스트 완료 확인 다이얼로그 =====

@Composable
private fun QuestVerifyDialog(
    quest: Quest,
    onDismiss: () -> Unit,
    onSubmit: (String) -> Unit
) {
    var answer  by remember { mutableStateOf("") }
    val context = LocalContext.current

    Dialog(onDismissRequest = onDismiss, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        Card(
            modifier  = Modifier.padding(horizontal = 20.dp).fillMaxWidth(),
            shape     = RoundedCornerShape(22.dp),
            colors    = CardDefaults.cardColors(containerColor = Color(0xFFFAF7F1)),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("퀘스트 완료 확인", fontSize = 15.sp, fontWeight = FontWeight.Medium, color = Color(0xFF3A3228))

                // 질문 박스
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF0ECE0), RoundedCornerShape(12.dp))
                        .padding(horizontal = 14.dp, vertical = 13.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text("질문", fontSize = 10.sp, color = Color(0xFF8A7A60))
                    Text(quest.confirmQuestion, fontSize = 13.sp, color = Color(0xFF3A3228), lineHeight = 20.sp)
                }

                // 답변 입력
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("내 답변", fontSize = 10.sp, color = Color(0xFF8A7A60))
                    OutlinedTextField(
                        value         = answer,
                        onValueChange = { answer = it },
                        modifier      = Modifier.fillMaxWidth().defaultMinSize(minHeight = 76.dp),
                        maxLines      = 4,
                        shape         = RoundedCornerShape(12.dp),
                        colors        = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor    = Color(0xFFD4CCB8),
                            unfocusedBorderColor  = Color(0xFFD4CCB8),
                            focusedContainerColor   = Color(0xFFF5F0E8),
                            unfocusedContainerColor = Color(0xFFF5F0E8)
                        ),
                        textStyle = androidx.compose.ui.text.TextStyle(fontSize = 13.sp, color = Color(0xFF3A3228))
                    )
                }

                // 제출 버튼
                Button(
                    onClick = {
                        if (answer.isBlank()) {
                            Toast.makeText(context, "답변을 입력해주세요", Toast.LENGTH_SHORT).show()
                        } else {
                            onSubmit(answer.trim())
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape    = RoundedCornerShape(14.dp),
                    colors   = ButtonDefaults.buttonColors(containerColor = Color(0xFF6A9858))
                ) {
                    Text("제출하기", color = Color.White, fontSize = 14.sp)
                }

                Text(
                    "취소",
                    modifier  = Modifier.fillMaxWidth().clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { onDismiss() },
                    textAlign = TextAlign.Center,
                    fontSize  = 12.sp,
                    color     = Color(0xFFB0A890)
                )
            }
        }
    }
}

// ===== 공통 컴포넌트 =====

@Composable
private fun StarRating(level: Int, starSize: TextUnit = 16.sp) {
    Row {
        repeat(5) { i ->
            Text("★", fontSize = starSize, color = if (i < level) Color(0xFFF0C030) else Color(0xFFE0D8C8))
        }
    }
}
