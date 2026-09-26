package com.jeepark.onestep.ui.components.dialogs

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.jeepark.onestep.data.model.Quest
import com.jeepark.onestep.ui.components.FlatCard
import com.jeepark.onestep.ui.components.PrimaryPillButton
import com.jeepark.onestep.ui.components.SecondaryPillButton
import com.jeepark.onestep.ui.components.StarRating
import com.jeepark.onestep.ui.theme.AmberText
import com.jeepark.onestep.ui.theme.HeadingText
import com.jeepark.onestep.ui.theme.MutedText

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
        FlatCard(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .graphicsLayer { scaleX = scale; scaleY = scale; this.alpha = alpha },
            cornerRadius = 22.dp
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(quest.questName, fontSize = 17.sp, fontWeight = FontWeight.Medium, color = HeadingText, lineHeight = 24.sp,
                    modifier = Modifier.padding(bottom = 12.dp))

                // 난이도 + 경험치 행
                Row(
                    modifier          = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    StarRating(quest.difficulty)
                    Spacer(modifier = Modifier.weight(1f))
                    Text("+${quest.questEXP} XP", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = AmberText)
                }

                // 버튼 행
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    SecondaryPillButton(
                        text = "넘기기",
                        onClick = onSkip,
                        modifier = Modifier.weight(1f),
                        minHeight = 48.dp
                    )
                    PrimaryPillButton(
                        text = "수락하기",
                        onClick = onAccept,
                        modifier = Modifier.weight(1f),
                        minHeight = 48.dp
                    )
                }

                Text(
                    "취소",
                    modifier  = Modifier.fillMaxWidth().clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { onDismiss() },
                    textAlign = TextAlign.Center,
                    fontSize  = 12.sp,
                    color     = MutedText
                )
            }
        }
    }
}
