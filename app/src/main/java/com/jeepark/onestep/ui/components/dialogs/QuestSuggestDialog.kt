package com.jeepark.onestep.ui.components.dialogs

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.jeepark.onestep.data.model.Quest
import com.jeepark.onestep.ui.components.StarRating

// ===== 퀘스트 추천 다이얼로그 =====

@Composable
fun QuestSuggestDialog(
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
