package com.jeepark.onestep.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeepark.onestep.data.model.User
import com.jeepark.onestep.ui.components.BottomButton
import com.jeepark.onestep.ui.components.InputWithWarning
import com.jeepark.onestep.ui.components.SelectButton
import com.jeepark.onestep.ui.components.TextInput
import com.jeepark.onestep.util.FirestoreRepository

private val SIGNUP_BG      = Color(0xFFFDF8F0)
private val SIGNUP_PRIMARY = Color(0xFF5A9848)
private val SIGNUP_CARD    = Color(0xFFF5EFE4)

@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    onNavigateToInitQuestion: () -> Unit,
    onNavigateToInit: () -> Unit,
    repository: FirestoreRepository = remember { FirestoreRepository() }
) {
    val context = LocalContext.current

    var nickname by remember { mutableStateOf("") }
    var age      by remember { mutableIntStateOf(0) }
    var gender   by remember { mutableStateOf(true) }
    val enabled  = nickname.isNotEmpty() && age != 0

    val nicknameRegex = "^[가-힣a-zA-Z0-9]*$".toRegex()
    val warning = if (!nickname.matches(nicknameRegex)) {
        "한글, 영어, 숫자만 가능합니다."
    } else {
        ""
    }

    Box(modifier = Modifier.fillMaxSize().background(SIGNUP_BG)) {

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

fun saveUserInFirebase(
    nickname: String,
    age: Int,
    gender: Boolean,
    repository: FirestoreRepository,
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
