package com.jeepark.onestep.ui.screens.survey

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jeepark.onestep.domain.rules.INIT_QUESTIONS
import com.jeepark.onestep.ui.components.PagerNavigationButton
import com.jeepark.onestep.ui.components.TextInput
import com.jeepark.onestep.ui.park.ParkBackground
import com.jeepark.onestep.ui.theme.CreamBackground
import com.jeepark.onestep.ui.theme.HeadingText
import com.jeepark.onestep.ui.theme.PrimaryGreen
import com.jeepark.onestep.ui.theme.SecondaryBackground
import com.jeepark.onestep.ui.theme.SecondaryBorder
import kotlinx.coroutines.delay

@Composable
fun InitQuestionScreen(
    modifier: Modifier = Modifier,
    onNavigateToMain: () -> Unit,
    onNavigateToInit: () -> Unit,
    vm: InitQuestionViewModel = viewModel(factory = InitQuestionViewModel.Factory)
) {
    // 뒤로가기 완전 차단 — 설문 완료 전까지 이탈 불가
    BackHandler { /* 막기 */ }

    val state by vm.state.collectAsState()
    val pagerState = rememberPagerState(pageCount = { state.pageCount })

    // 현재 문항은 ViewModel이 정하고, 페이저는 그 문항으로 넘어가기만 한다
    LaunchedEffect(state.currentPage) {
        if (pagerState.currentPage != state.currentPage) pagerState.scrollToPage(state.currentPage)
    }

    LaunchedEffect(Unit) {
        vm.events.collect { event ->
            when (event) {
                InitQuestionEvent.Completed -> onNavigateToMain()
                InitQuestionEvent.Failed    -> onNavigateToInit()
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(CreamBackground)) {

        // 상단 공원 헤더
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
        ) {
            ParkBackground(tier = 0, modifier = Modifier.fillMaxSize())

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 28.dp, bottom = 20.dp)
            ) {
                Text(
                    text       = "나에 대해 알려주세요",
                    fontSize   = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color      = Color.White
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text     = "${state.currentPage + 1} / ${state.pageCount}",
                    fontSize = 13.sp,
                    color    = Color.White.copy(alpha = 0.85f)
                )
            }
        }

        // 진행 바
        LinearProgressIndicator(
            progress          = { (state.currentPage + 1) / state.pageCount.toFloat() },
            modifier          = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .padding(top = 160.dp),
            color             = PrimaryGreen,
            trackColor        = SecondaryBackground,
            strokeCap         = StrokeCap.Round
        )

        // 콘텐츠
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 164.dp)
                .imePadding(),
            verticalArrangement = Arrangement.Top
        ) {
            HorizontalPager(
                state            = pagerState,
                userScrollEnabled = false,
            ) { pageIndex ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .padding(horizontal = 28.dp)
                        .padding(top = 32.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text       = INIT_QUESTIONS[pageIndex].title,
                        fontSize   = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color      = HeadingText,
                        lineHeight = 32.sp
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    val focusRequester = remember { FocusRequester() }
                    LaunchedEffect(pagerState.currentPage) {
                        if (pagerState.currentPage == pageIndex) {
                            delay(100)
                            focusRequester.requestFocus()
                        }
                    }

                    TextInput(
                        modifier      = Modifier.focusRequester(focusRequester),
                        isDigit       = true,
                        placeholder   = "답변을 입력해주세요",
                        onValueChange = { newValue -> vm.onAnswerChange(pageIndex, newValue) },
                        text          = state.answers[pageIndex]?.toString() ?: "",
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // 하단 네비게이션
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp, vertical = 20.dp)
            ) {
                if (state.canGoBack) {
                    PagerNavigationButton(
                        icon      = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        modifier  = Modifier.align(Alignment.CenterStart),
                        onClick   = vm::goBack
                    )
                }

                // 페이지 도트
                Row(
                    modifier              = Modifier.align(Alignment.Center),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment     = Alignment.CenterVertically
                ) {
                    repeat(state.pageCount) { i ->
                        val isCurrent = state.currentPage == i
                        Box(
                            modifier = Modifier
                                .size(if (isCurrent) 10.dp else 7.dp)
                                .clip(CircleShape)
                                .background(if (isCurrent) PrimaryGreen else SecondaryBorder)
                        )
                    }
                }

                if (state.canGoNext) {
                    if (!state.isLastPage) {
                        PagerNavigationButton(
                            icon     = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            modifier = Modifier.align(Alignment.CenterEnd),
                            onClick  = vm::goNext
                        )
                    } else {
                        TextButton(
                            modifier = Modifier.align(Alignment.CenterEnd),
                            enabled  = !state.isSubmitting,
                            onClick  = vm::submit,
                        ) {
                            if (state.isSubmitting) {
                                CircularProgressIndicator(
                                    modifier    = Modifier.size(20.dp),
                                    color       = PrimaryGreen,
                                    strokeWidth = 2.dp
                                )
                            } else {
                                Text(
                                    text       = "완료",
                                    fontSize   = 16.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color      = PrimaryGreen
                                )
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(12.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewInitQuestionScreen() {
    InitQuestionScreen(onNavigateToMain = {}, onNavigateToInit = {})
}
