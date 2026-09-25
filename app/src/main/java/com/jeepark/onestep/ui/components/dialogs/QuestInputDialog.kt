package com.jeepark.onestep.ui.components.dialogs

import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

// ===== 퀘스트 조건 다이얼로그 =====

@Composable
fun QuestInputDialog(
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
