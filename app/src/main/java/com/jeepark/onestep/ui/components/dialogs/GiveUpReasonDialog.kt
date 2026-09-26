package com.jeepark.onestep.ui.components.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.jeepark.onestep.ui.components.FlatCard
import com.jeepark.onestep.ui.components.PrimaryPillButton
import com.jeepark.onestep.ui.theme.HeadingText
import com.jeepark.onestep.ui.theme.MutedText
import com.jeepark.onestep.ui.theme.PrimaryGreen
import com.jeepark.onestep.ui.theme.SecondaryBackground
import com.jeepark.onestep.ui.theme.SecondaryBorder

// ===== 퀘스트 포기 사유 다이얼로그 =====

@Composable
fun GiveUpReasonDialog(
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
        FlatCard(
            modifier = Modifier.padding(horizontal = 20.dp).fillMaxWidth(),
            cornerRadius = 22.dp
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("포기 사유를 선택해주세요", fontSize = 15.sp, fontWeight = FontWeight.Medium, color = HeadingText)

                reasons.forEach { (id, label) ->
                    val isSelected = selected == id
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (isSelected) PrimaryGreen.copy(alpha = 0.14f) else SecondaryBackground)
                            .border(1.dp, if (isSelected) PrimaryGreen else SecondaryBorder, RoundedCornerShape(12.dp))
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) { selected = id }
                            .padding(horizontal = 16.dp, vertical = 14.dp)
                    ) {
                        Text(
                            text      = label,
                            fontSize  = 13.sp,
                            color     = if (isSelected) PrimaryGreen else MutedText,
                            fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal
                        )
                    }
                }

                PrimaryPillButton(
                    text = "포기하기",
                    onClick = { if (selected >= 0) onSubmit(selected) },
                    enabled = selected >= 0,
                    minHeight = 52.dp
                )

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
