package com.jeepark.onestep.ui.screens

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
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.jeepark.onestep.data.model.InitQuestions
import com.jeepark.onestep.ui.components.PagerNavigationButton
import com.jeepark.onestep.ui.components.TextInput
import com.jeepark.onestep.util.FirestoreRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private val IQ_BG      = Color(0xFFFDF8F0)
private val IQ_PRIMARY = Color(0xFF5A9848)
private val IQ_CARD    = Color(0xFFF5EFE4)

@Composable
fun InitQuestionScreen(
    modifier: Modifier = Modifier,
    onNavigateToMain: () -> Unit,
    onNavigateToInit: () -> Unit,
    repository: FirestoreRepository = remember { FirestoreRepository() }
) {
    // 뒤로가기 완전 차단 — 설문 완료 전까지 이탈 불가
    BackHandler { /* 막기 */ }

    val answers    = remember { mutableStateListOf(-1, -1, -1, -1, -1, -1) }
    var isLoading  by remember { mutableStateOf(false) }
    val scope      = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { 6 })
    val questions  = listOf(
        "어제 식사 횟수",
        "어제 수면 시간",
        "지난 일주일 동안의\n샤워 횟수",
        "지난 일주일 동안\n밖에 나간 일 수",
        "일이나 학업을\n하지 않은 기간 (월)",
        "주된 활동 시간\n0(새벽) 1(오전) 2(오후) 3(저녁)"
    )
    // 각 문항의 (최솟값, 최댓값)
    val ranges = listOf(
        0 to 10,   // 식사 횟수
        0 to 24,   // 수면 시간
        0 to 7,   // 샤워 횟수
        0 to 7,    // 외출 일 수
        0 to 600,  // 미취업 기간 (월)
        0 to 3     // 활동 시간대
    )

    Box(modifier = Modifier.fillMaxSize().background(IQ_BG)) {

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
                    text     = "${pagerState.currentPage + 1} / 6",
                    fontSize = 13.sp,
                    color    = Color.White.copy(alpha = 0.85f)
                )
            }
        }

        // 진행 바
        LinearProgressIndicator(
            progress          = { (pagerState.currentPage + 1) / 6f },
            modifier          = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .padding(top = 160.dp),
            color             = IQ_PRIMARY,
            trackColor        = IQ_CARD,
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
                        text       = questions[pageIndex],
                        fontSize   = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color      = Color(0xFF2A2A2A),
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

                    val (minVal, maxVal) = ranges[pageIndex]
                    TextInput(
                        modifier      = Modifier.focusRequester(focusRequester),
                        isDigit       = true,
                        placeholder   = "답변을 입력해주세요",
                        onValueChange = { newValue ->
                            val v = newValue.toIntOrNull()
                            answers[pageIndex] = if (v == null) -1 else v.coerceIn(minVal, maxVal)
                        },
                        text = if (answers[pageIndex] == -1) "" else answers[pageIndex].toString(),
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
                if (pagerState.currentPage > 0) {
                    PagerNavigationButton(
                        icon      = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        modifier  = Modifier.align(Alignment.CenterStart),
                        onClick   = {
                            scope.launch {
                                if (pagerState.currentPage > 0)
                                    pagerState.scrollToPage(pagerState.currentPage - 1)
                            }
                        }
                    )
                }

                // 페이지 도트
                Row(
                    modifier              = Modifier.align(Alignment.Center),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment     = Alignment.CenterVertically
                ) {
                    repeat(pagerState.pageCount) { i ->
                        val isCurrent = pagerState.currentPage == i
                        Box(
                            modifier = Modifier
                                .size(if (isCurrent) 10.dp else 7.dp)
                                .clip(CircleShape)
                                .background(if (isCurrent) IQ_PRIMARY else Color(0xFFCCCCCC))
                        )
                    }
                }

                if (answers[pagerState.currentPage] >= 0) {
                    if (pagerState.currentPage < 5) {
                        PagerNavigationButton(
                            icon     = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            modifier = Modifier.align(Alignment.CenterEnd),
                            onClick  = {
                                scope.launch {
                                    pagerState.scrollToPage(pagerState.currentPage + 1)
                                }
                            }
                        )
                    } else {
                        TextButton(
                            modifier = Modifier.align(Alignment.CenterEnd),
                            enabled  = !isLoading,
                            onClick  = {
                                isLoading = true
                                onClickSubmit(
                                    onNavigateToMain = onNavigateToMain,
                                    onNavigateToInit = {
                                        isLoading = false
                                        onNavigateToInit()
                                    },
                                    answers    = answers,
                                    repository = repository
                                )
                            },
                        ) {
                            if (isLoading) {
                                CircularProgressIndicator(
                                    modifier    = Modifier.size(20.dp),
                                    color       = IQ_PRIMARY,
                                    strokeWidth = 2.dp
                                )
                            } else {
                                Text(
                                    text       = "완료",
                                    fontSize   = 16.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color      = IQ_PRIMARY
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

fun onClickSubmit(
    onNavigateToMain: () -> Unit,
    onNavigateToInit: () -> Unit,
    answers: List<Int>,
    repository: FirestoreRepository
) {
    val initQ = InitQuestions(
        meal       = answers[0],
        sleepTime  = answers[1],
        shower     = answers[2],
        outside    = answers[3],
        hiki       = answers[4],
        activeTime = answers[5],
    )

    repository.saveInitQuestions(
        data      = initQ,
        onSuccess = { onNavigateToMain() },
        onFailure = { onNavigateToInit() },
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewInitQuestionScreen() {
    InitQuestionScreen(onNavigateToMain = {}, onNavigateToInit = {})
}
