package com.jeepark.onestep.ui.components.dialogs

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.jeepark.onestep.data.model.Quest
import com.jeepark.onestep.ui.components.FlatCard
import com.jeepark.onestep.ui.components.PrimaryPillButton
import com.jeepark.onestep.ui.theme.HeadingText
import com.jeepark.onestep.ui.theme.MutedText
import com.jeepark.onestep.ui.theme.SecondaryBackground
import com.jeepark.onestep.ui.theme.SecondaryBorder

// ===== 퀘스트 완료 확인 다이얼로그 =====
// 답변은 필수이며 건너뛰기 버튼이 없다 — 이 동작은 절대 바꾸지 않는다.

@Composable
fun QuestVerifyDialog(
    quest: Quest,
    isSaving: Boolean,
    onDismiss: () -> Unit,
    onSubmit: (String) -> Unit
) {
    var answer  by remember { mutableStateOf("") }
    val context = LocalContext.current

    Dialog(onDismissRequest = onDismiss, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        FlatCard(
            modifier = Modifier.padding(horizontal = 20.dp).fillMaxWidth(),
            cornerRadius = 22.dp
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("퀘스트 완료 확인", fontSize = 15.sp, fontWeight = FontWeight.Medium, color = HeadingText)

                // 질문 박스
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(SecondaryBackground, RoundedCornerShape(12.dp))
                        .padding(horizontal = 14.dp, vertical = 13.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text("질문", fontSize = 10.sp, color = MutedText)
                    Text(quest.confirmQuestion, fontSize = 13.sp, color = HeadingText, lineHeight = 20.sp)
                }

                // 답변 입력
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("내 답변", fontSize = 10.sp, color = MutedText)
                    OutlinedTextField(
                        value         = answer,
                        onValueChange = { answer = it },
                        modifier      = Modifier.fillMaxWidth().defaultMinSize(minHeight = 76.dp),
                        maxLines      = 4,
                        shape         = RoundedCornerShape(12.dp),
                        colors        = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor    = SecondaryBorder,
                            unfocusedBorderColor  = SecondaryBorder,
                            focusedContainerColor   = SecondaryBackground,
                            unfocusedContainerColor = SecondaryBackground
                        ),
                        textStyle = TextStyle(fontSize = 13.sp, color = HeadingText)
                    )
                }

                // 제출 버튼 — 답변이 비어있으면 제출되지 않는다 (필수, 건너뛰기 없음)
                PrimaryPillButton(
                    text = if (isSaving) "저장 중..." else "제출하기",
                    onClick = {
                        if (answer.isBlank()) {
                            Toast.makeText(context, "답변을 입력해주세요", Toast.LENGTH_SHORT).show()
                        } else {
                            onSubmit(answer.trim())
                        }
                    },
                    enabled = !isSaving,
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
