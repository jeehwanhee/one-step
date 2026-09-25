package com.jeepark.onestep.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jeepark.onestep.data.model.Quest
import com.jeepark.onestep.ui.components.StarRating
import com.jeepark.onestep.ui.components.dialogs.GiveUpReasonDialog
import com.jeepark.onestep.ui.components.dialogs.QuestInputDialog
import com.jeepark.onestep.ui.components.dialogs.QuestSuggestDialog
import com.jeepark.onestep.ui.components.dialogs.QuestVerifyDialog
import com.jeepark.onestep.ui.viewmodels.MainViewModel
import kotlinx.coroutines.launch

// ===== 메인 화면 =====

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
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
    val isSavingQuest by vm.isSavingQuest.collectAsState()
    val loadError by vm.loadError.collectAsState()
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

    LaunchedEffect(showTierUp) {
        if (showTierUp) {
            snackbarHostState.showSnackbar("새 친구가 나타났어요!")
            showTierUp = false
        }
    }

    LaunchedEffect(loadError) {
        if (loadError != null) {
            val result = snackbarHostState.showSnackbar(
                message    = loadError ?: "정보를 불러오지 못했어요",
                actionLabel = "다시 시도"
            )
            if (result == SnackbarResult.ActionPerformed) vm.loadUser()
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
        ParkBackground(tier = tier)

        // 활성 퀘스트가 없을 때만 퀘스트 버튼 표시
        if (activeQuest == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 14.dp, vertical = 24.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                QuestButton(limitReached = false) { showInputDialog = true }
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
    }

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
                isSaving  = isSavingQuest,
                onDismiss = { showVerifyDialog = false },
                onSubmit  = { answer ->
                    vm.saveCompletedQuest(
                        quest     = quest,
                        answer    = answer,
                        onTierUp  = { showTierUp = true },
                        onSuccess = {
                            showVerifyDialog = false
                            vm.clearActiveQuest()
                        },
                        onError = { msg ->
                            // activeQuest는 그대로 유지 → 카드가 남아있어 다시 시도 가능
                            showVerifyDialog = false
                            scope.launch { snackbarHostState.showSnackbar(msg) }
                        }
                    )
                }
            )
        }
        if (showGiveUpDialog) {
            GiveUpReasonDialog(
                onDismiss = { showGiveUpDialog = false },
                onSubmit  = { reason ->
                    showGiveUpDialog = false
                    vm.saveGiveUpQuest(
                        quest     = quest,
                        reason    = reason,
                        onSuccess = { vm.clearActiveQuest() },
                        onError   = { msg ->
                            // activeQuest는 그대로 유지 → 다시 포기를 시도할 수 있음
                            scope.launch { snackbarHostState.showSnackbar(msg) }
                        }
                    )
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

// ===== 퀘스트 수행 카드 =====

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
