package com.jeepark.onestep.ui.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeepark.onestep.data.model.User
import com.jeepark.onestep.ui.components.BottomButton
import com.jeepark.onestep.ui.components.InputWithWarning
import com.jeepark.onestep.ui.components.SelectButton
import com.jeepark.onestep.ui.components.TextInput
import com.jeepark.onestep.data.repository.UserRepository
import com.jeepark.onestep.ui.theme.CreamBackground
import com.jeepark.onestep.ui.theme.MutedText
import com.jeepark.onestep.ui.theme.PrimaryGreen
import com.jeepark.onestep.ui.theme.SecondaryBorder

@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    onNavigateToInitQuestion: () -> Unit,
    onNavigateToInit: () -> Unit,
    repository: UserRepository = remember { UserRepository() }
) {
    val context = LocalContext.current

    var nickname       by remember { mutableStateOf("") }
    var age            by remember { mutableIntStateOf(0) }
    var gender         by remember { mutableStateOf(true) }
    var agreeTerms     by remember { mutableStateOf(false) }
    var agreePrivacy   by remember { mutableStateOf(false) }
    val enabled  = nickname.isNotEmpty() && age != 0 && agreeTerms && agreePrivacy

    val nicknameRegex = "^[가-힣a-zA-Z0-9]*$".toRegex()
    val warning = if (!nickname.matches(nicknameRegex)) {
        "한글, 영어, 숫자만 가능합니다."
    } else {
        ""
    }

    Box(modifier = Modifier.fillMaxSize().background(CreamBackground)) {

        // 상단 그린 헤더
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        ) {
            ParkBackground(tier = 0, modifier = Modifier.fillMaxSize())

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 28.dp, bottom = 20.dp)
            ) {
                Text(
                    text       = "프로필 만들기",
                    fontSize   = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color      = Color.White
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text     = "나에 대해 알려주세요.",
                    fontSize = 13.sp,
                    color    = Color.White.copy(alpha = 0.85f)
                )
            }
        }

        // 콘텐츠 영역
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 200.dp, start = 28.dp, end = 28.dp, bottom = 24.dp),
            horizontalAlignment = Alignment.Start
        ) {
            InputWithWarning(
                inputComp = {
                    TextInput(
                        placeholder   = "닉네임",
                        onValueChange = { newValue -> nickname = newValue },
                        text          = nickname,
                    )
                },
                warningText = warning
            )

            Spacer(modifier = Modifier.height(12.dp))

            if (nickname.isNotEmpty()) {
                TextInput(
                    isDigit       = true,
                    placeholder   = "나이",
                    onValueChange = { newValue -> age = newValue.toIntOrNull() ?: 0 },
                    text          = if (age != 0) age.toString() else "",
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (nickname.isNotEmpty() && age != 0) {
                SelectButton(
                    leftText     = "남자",
                    rightText    = "여자",
                    leftOnClick  = { gender = true },
                    rightOnClick = { gender = false },
                    isSelectedLeft = gender
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // 약관 동의
            ConsentRow(
                checked  = agreeTerms,
                onToggle = { agreeTerms = !agreeTerms },
                label    = "이용약관",
                url      = "https://marmalade-locket-e42.notion.site/33c74db951cd80249c4dc61ed6817ba6?source=copy_link",
                context  = context
            )
            Spacer(Modifier.height(8.dp))
            ConsentRow(
                checked  = agreePrivacy,
                onToggle = { agreePrivacy = !agreePrivacy },
                label    = "개인정보 처리방침",
                url      = "https://marmalade-locket-e42.notion.site/33c74db951cd8022a9c2f5d195d648b7?source=copy_link",
                context  = context
            )
            Spacer(Modifier.height(16.dp))

            BottomButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                text    = "프로필 만들기",
                onClick = {
                    saveUserInFirebase(
                        nickname,
                        age,
                        gender,
                        repository,
                        onNavigateToInitQuestion,
                        onNavigateToInit,
                        context
                    )
                },
                enabled = enabled,
            )
        }
    }
}

@Composable
private fun ConsentRow(
    checked: Boolean,
    onToggle: () -> Unit,
    label: String,
    url: String,
    context: android.content.Context
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier          = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector        = if (checked) Icons.Filled.CheckCircle else Icons.Outlined.CheckCircle,
            contentDescription = null,
            tint               = if (checked) PrimaryGreen else SecondaryBorder,
            modifier           = Modifier
                .size(22.dp)
                .clickable(
                    indication        = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick           = onToggle
                )
        )
        Spacer(Modifier.size(8.dp))
        Text(
            text = buildAnnotatedString {
                append("(필수) ")
                withStyle(SpanStyle(textDecoration = TextDecoration.Underline, color = PrimaryGreen)) {
                    append(label)
                }
                append("에 동의합니다.")
            },
            fontSize = 13.sp,
            color    = MutedText,
            modifier = Modifier.clickable(
                indication        = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick           = {
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                }
            )
        )
    }
}

fun saveUserInFirebase(
    nickname: String,
    age: Int,
    gender: Boolean,
    repository: UserRepository,
    onNavigateToInitQuestion: () -> Unit,
    onNavigateToInit: () -> Unit,
    context: android.content.Context
) {
    repository.saveInitUser(
        nickname  = nickname,
        age       = age,
        gender    = gender,
        onSuccess = { onNavigateToInitQuestion() },
        onFailure = { e ->
            Toast.makeText(context, "회원가입에 실패했습니다: ${e.message}", Toast.LENGTH_SHORT).show()
            onNavigateToInit()
        }
    )
}
