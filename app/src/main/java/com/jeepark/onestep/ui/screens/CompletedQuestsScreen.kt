package com.jeepark.onestep.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jeepark.onestep.data.model.PrevQuest
import com.jeepark.onestep.ui.viewmodels.CollectionViewModel

private val CQ_BG      = Color(0xFFFDF8F0)
private val CQ_PRIMARY = Color(0xFF5A9848)

@Composable
fun CompletedQuestsScreen(
    modifier: Modifier = Modifier,
    vm: CollectionViewModel = viewModel(),
) {
    val completedQuests by vm.completedQuests.collectAsState()

    Box(modifier = modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(CQ_BG)
        ) {
            CompletedHeader(count = completedQuests.size)

            if (completedQuests.isEmpty()) {
                Box(
                    modifier         = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text       = "아직 완료한 퀘스트가 없어요\n첫 퀘스트에 도전해 보세요!",
                        fontSize   = 14.sp,
                        color      = Color(0xFFAAAAAA),
                        lineHeight = 22.sp
                    )
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(top = 8.dp, bottom = 32.dp)
                ) {
                    items(completedQuests) { quest ->
                        CompletedQuestCard(
                            quest    = quest,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CompletedHeader(count: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(top = 8.dp, bottom = 12.dp)
    ) {
        Row(
            modifier          = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text       = "완료한 퀘스트",
                fontSize   = 18.sp,
                fontWeight = FontWeight.Bold,
                color      = Color(0xFF2A2A2A)
            )
            Spacer(Modifier.weight(1f))
            Text(
                text     = "${count}개",
                fontSize = 13.sp,
                color    = CQ_PRIMARY
            )
        }
    }
}

@Composable
private fun CompletedQuestCard(quest: PrevQuest, modifier: Modifier = Modifier) {
    Card(
        modifier  = modifier.fillMaxWidth(),
        shape     = RoundedCornerShape(12.dp),
        colors    = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
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
                    color      = Color(0xFF2A2A2A),
                    modifier   = Modifier.weight(1f)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text     = quest.doneDate.take(10),
                    fontSize = 11.sp,
                    color    = Color(0xFFAAAAAA)
                )
            }

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
                    color      = CQ_PRIMARY,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
