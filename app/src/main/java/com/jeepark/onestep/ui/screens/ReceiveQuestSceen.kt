package com.jeepark.onestep.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jeepark.onestep.ui.components.BottomButton
import com.jeepark.onestep.ui.theme.black
import com.jeepark.onestep.ui.viewmodels.ReceiveQuestViewModel

private val MOOD_SELECTED = Color(0xFF5A9848)
private val MOOD_DEFAULT  = Color(0xFFF0EAE0)

private val MOODS = listOf("매우 나쁨", "나쁨", "보통", "좋음", "매우 좋음")

@Composable
fun ReceiveQuestScreen(
    modifier: Modifier = Modifier,
    vm: ReceiveQuestViewModel = viewModel(),
    onNavigateToQuest: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 120.dp, bottom = 60.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text       = "오늘 기분이 어때요?",
            fontSize   = 20.sp,
            fontWeight = FontWeight.Bold,
            color      = Color(0xFF2A2A2A)
        )

        Spacer(Modifier.height(20.dp))

        // 기분 버튼
        // 1행: 나쁨/보통/좋음 (3개 — 짧은 텍스트)
        // 2행: 매우 나쁨/매우 좋음 (2개 — 긴 텍스트, 더 넓은 버튼)
        val row1 = listOf("나쁨", "보통", "좋음")
        val row2 = listOf("매우 나쁨", "매우 좋음")

        Row(
            modifier              = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            row1.forEach { mood -> MoodButton(mood, vm.selectedMood == mood) { vm.onMoodSelected(mood) } }
        }
        Spacer(Modifier.height(6.dp))
        Row(
            modifier              = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            row2.forEach { mood -> MoodButton(mood, vm.selectedMood == mood) { vm.onMoodSelected(mood) } }
        }

        Spacer(Modifier.weight(1f))

        Box(
            modifier        = Modifier
                .fillMaxWidth()
                .height(50.dp),
            contentAlignment = Alignment.Center
        ) {
            if (vm.isLoading) {
                CircularProgressIndicator(
                    modifier    = Modifier.size(30.dp),
                    color       = black,
                    strokeWidth = 3.dp
                )
            } else {
                BottomButton(
                    text    = "퀘스트 받기",
                    onClick = { vm.fetchQuestData { onNavigateToQuest() } },
                    enabled = vm.isButtonEnabled
                )
            }
        }
    }
}

@Composable
private fun RowScope.MoodButton(text: String, selected: Boolean, onClick: () -> Unit) {
    Button(
        onClick        = onClick,
        modifier       = Modifier
            .weight(1f)
            .height(48.dp),
        shape          = RoundedCornerShape(12.dp),
        colors         = ButtonDefaults.buttonColors(
            containerColor = if (selected) MOOD_SELECTED else MOOD_DEFAULT,
            contentColor   = if (selected) Color.White else Color(0xFF4A4A4A)
        ),
        contentPadding = PaddingValues(horizontal = 4.dp, vertical = 0.dp),
        elevation      = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
    ) {
        Text(
            text       = text,
            fontSize   = 13.sp,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            maxLines   = 1,
            softWrap   = false
        )
    }
}
