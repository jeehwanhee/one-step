package com.jeepark.onestep.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jeepark.onestep.ui.viewmodels.InitViewModel
import com.jeepark.onestep.ui.viewmodels.StartDestination

@Composable
fun InitScreen(
    onNavigateToAuth: () -> Unit,
    onNavigateToMain: () -> Unit,
    onNavigateToInitQuestion: () -> Unit,
    vm: InitViewModel = viewModel(factory = InitViewModel.Factory)
) {
    LaunchedEffect(Unit) {
        vm.destination.collect { destination ->
            when (destination) {
                StartDestination.Auth         -> onNavigateToAuth()
                StartDestination.InitQuestion -> onNavigateToInitQuestion()
                StartDestination.Main         -> onNavigateToMain()
            }
        }
    }

    val alpha = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        alpha.animateTo(1f, tween(600))
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer { this.alpha = alpha.value }
    ) {
        ParkBackground(tier = 0)

        // 앱 타이틀
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 36.dp, bottom = 160.dp)
        ) {
            Text(
                text       = "한걸음,",
                fontSize   = 42.sp,
                fontWeight = FontWeight.Bold,
                color      = Color.White
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text     = "오늘도 한 걸음씩",
                fontSize = 16.sp,
                color    = Color.White.copy(alpha = 0.85f)
            )
        }

        // 로딩 인디케이터
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 60.dp)
                .clip(RoundedCornerShape(50))
                .background(Color.White.copy(alpha = 0.2f))
                .padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            CircularProgressIndicator(
                modifier    = Modifier.size(22.dp),
                color       = Color.White,
                strokeWidth = 2.dp
            )
        }
    }
}
