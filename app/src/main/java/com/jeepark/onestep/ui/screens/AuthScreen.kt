package com.jeepark.onestep.ui.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jeepark.onestep.ui.components.BottomButton
import com.jeepark.onestep.ui.theme.CreamBackground
import com.jeepark.onestep.ui.theme.HeadingText
import com.jeepark.onestep.ui.theme.MutedText
import com.jeepark.onestep.ui.theme.SecondaryBorder
import com.jeepark.onestep.ui.theme.white
import com.jeepark.onestep.ui.viewmodels.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun AuthScreen(
    onNavigateToMain: () -> Unit,
    onNavigateToSignup: () -> Unit,
    screenModel: AuthViewModel = viewModel()
) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        screenModel.login(
            data           = result.data,
            onNewUser      = onNavigateToSignup,
            onExistingUser = onNavigateToMain,
            onError        = {
                scope.launch {
                    snackbarHostState.showSnackbar("로그인에 실패했어요. 다시 시도해주세요.")
                }
            }
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {

        // 상단 공원 배경 (화면의 55%)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.55f)
        ) {
            ParkBackground(tier = 0)

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 36.dp, bottom = 28.dp)
            ) {
                Text(
                    text       = "한걸음,",
                    fontSize   = 38.sp,
                    fontWeight = FontWeight.Bold,
                    color      = Color.White
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text     = "오늘도 한 걸음씩",
                    fontSize = 15.sp,
                    color    = Color.White.copy(alpha = 0.85f)
                )
            }
        }

        // 하단 크림 카드
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .fillMaxHeight(0.52f),
            shape  = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
            colors = CardDefaults.cardColors(containerColor = CreamBackground),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 36.dp, vertical = 36.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text       = "시작해볼까요?",
                        fontSize   = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color      = HeadingText
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text     = "구글 계정으로 간편하게 로그인하세요.",
                        fontSize = 14.sp,
                        color    = MutedText
                    )
                }

                BottomButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    text = "Google 로그인",
                    onClick = {
                        val signInClient = screenModel.getGoogleSignInClient(context)
                        googleSignInLauncher.launch(signInClient.signInIntent)
                    },
                    textColor = HeadingText,
                    containerColor = white,
                    borderColor = SecondaryBorder,
                    borderWidth = 2
                )
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier  = Modifier.align(Alignment.TopCenter)
        )
    }
}
