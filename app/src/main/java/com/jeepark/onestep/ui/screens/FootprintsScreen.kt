package com.jeepark.onestep.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jeepark.onestep.data.model.PrevQuest
import com.jeepark.onestep.ui.components.FlatCard
import com.jeepark.onestep.ui.screens.footprints.QuestActivityCalendar
import com.jeepark.onestep.ui.screens.footprints.StatsColumn
import com.jeepark.onestep.ui.screens.footprints.calcAvgDifficulty
import com.jeepark.onestep.ui.screens.footprints.calcDday
import com.jeepark.onestep.ui.screens.footprints.calcMaxStreakDays
import com.jeepark.onestep.ui.screens.footprints.calcStartDate
import com.jeepark.onestep.ui.screens.footprints.calcStreakDays
import com.jeepark.onestep.ui.screens.footprints.calcTotalExp
import com.jeepark.onestep.ui.theme.AmberText
import com.jeepark.onestep.ui.theme.CardSurface
import com.jeepark.onestep.ui.theme.CreamBackground
import com.jeepark.onestep.ui.theme.CreamSurface
import com.jeepark.onestep.ui.theme.HeadingText
import com.jeepark.onestep.ui.theme.MutedText
import com.jeepark.onestep.ui.viewmodels.CollectionViewModel

@Composable
fun FootprintsScreen(
    modifier: Modifier = Modifier,
    vm: CollectionViewModel = viewModel(),
) {
    val user by vm.user.collectAsState()
    val completedQuests by vm.completedQuests.collectAsState()
    var selectedTab by remember { mutableStateOf(0) } // 0 = 이전 퀘스트, 1 = 통계

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(CreamBackground)
        ) {
            FootprintsHeader(
                selectedTab = selectedTab,
                onSelectTab = { selectedTab = it }
            )

            when (selectedTab) {
                0 -> HistoryTab(completedQuests)
                else -> StatsTab(isolatedHistory = user?.isolatedHistory ?: emptyList(), completedQuests = completedQuests)
            }
        }
    }
}

@Composable
private fun FootprintsHeader(selectedTab: Int, onSelectTab: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(CreamSurface)
    ) {
        Text(
            text       = "발자취",
            fontSize   = 20.sp,
            fontWeight = FontWeight.Bold,
            color      = HeadingText,
            modifier   = Modifier.padding(start = 20.dp, end = 20.dp, top = 32.dp, bottom = 14.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            TabButton(text = "이전 퀘스트", selected = selectedTab == 0, modifier = Modifier.weight(1f)) { onSelectTab(0) }
            TabButton(text = "통계", selected = selectedTab == 1, modifier = Modifier.weight(1f)) { onSelectTab(1) }
        }
    }
}

@Composable
private fun TabButton(text: String, selected: Boolean, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            .background(if (selected) CreamBackground else Color.Transparent)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick
            )
            .padding(vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = if (selected) HeadingText else MutedText
        )
    }
}

// ===== 탭: 이전 퀘스트 =====

@Composable
private fun HistoryTab(completedQuests: List<PrevQuest>) {
    if (completedQuests.isEmpty()) {
        Box(
            modifier         = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text       = "아직 완료한 퀘스트가 없어요\n첫 퀘스트에 도전해 보세요!",
                fontSize   = 14.sp,
                color      = MutedText,
                lineHeight = 22.sp
            )
        }
    } else {
        LazyColumn(
            contentPadding = PaddingValues(top = 12.dp, bottom = 32.dp)
        ) {
            item {
                Text(
                    text     = "완료한 퀘스트 · ${completedQuests.size}개",
                    fontSize = 12.5.sp,
                    color    = MutedText,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 4.dp)
                )
            }
            items(completedQuests) { quest ->
                CompletedQuestCard(
                    quest    = quest,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                )
            }
        }
    }
}

@Composable
private fun CompletedQuestCard(quest: PrevQuest, modifier: Modifier = Modifier) {
    FlatCard(modifier = modifier.fillMaxWidth(), backgroundColor = CardSurface, cornerRadius = 18.dp, shadowOffset = 2.dp) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier              = Modifier.fillMaxWidth(),
                verticalAlignment     = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text       = quest.questName,
                    fontSize   = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color      = HeadingText,
                    modifier   = Modifier.weight(1f)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text     = quest.doneDate.take(10),
                    fontSize = 11.sp,
                    color    = MutedText
                )
            }

            Spacer(Modifier.height(8.dp))
            Text(text = "Q. ${quest.confirmQuestion}", fontSize = 12.sp, color = MutedText)
            Spacer(Modifier.height(4.dp))
            Text(text = "A. ${quest.confirmAnswer}", fontSize = 12.sp, color = HeadingText)

            Spacer(Modifier.height(10.dp))
            Row(
                modifier          = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
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
                    color      = AmberText,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

// ===== 탭: 통계 =====

@Composable
private fun StatsTab(
    isolatedHistory: List<com.jeepark.onestep.data.model.IsolatedRecord>,
    completedQuests: List<PrevQuest>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 14.dp)
    ) {
        QuestActivityCalendar(completedQuests = completedQuests)

        Spacer(Modifier.height(14.dp))

        RecoveryGraph(history = isolatedHistory)

        Spacer(Modifier.height(14.dp))

        StatsColumn(
            completedCount = completedQuests.size,
            streakDays     = calcStreakDays(completedQuests),
            maxStreakDays  = calcMaxStreakDays(completedQuests),
            startDate      = calcStartDate(completedQuests),
            dday           = calcDday(completedQuests),
            totalExp       = calcTotalExp(completedQuests),
            avgDifficulty  = calcAvgDifficulty(completedQuests)
        )

        Spacer(Modifier.height(24.dp))
    }
}
