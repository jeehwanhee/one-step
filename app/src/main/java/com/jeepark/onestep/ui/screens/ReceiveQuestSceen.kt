package com.jeepark.onestep.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jeepark.onestep.ui.components.BottomButton
import com.jeepark.onestep.ui.components.DropdownInput
import com.jeepark.onestep.ui.components.SelectButton
import com.jeepark.onestep.ui.theme.black
import com.jeepark.onestep.ui.viewmodels.ReceiveQuestViewModel

@Composable
fun ReceiveQuestScreen(
    modifier: Modifier = Modifier,
    vm: ReceiveQuestViewModel = viewModel(),
    onNavigateToQuest: () -> Unit
) {
    val options = listOf("매우 나쁨", "나쁨", "보통", "좋음", "매우 좋음")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 160.dp,
                start = 60.dp,
                end = 60.dp,
                bottom = 60.dp
            ),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        DropdownInput(
            options = options,
            selectedText = vm.selectedMood,
            onOptionSelected = { index ->
                vm.onMoodSelected(options[index])
            },
            placeholder = "현재 기분은?"
        )

        Spacer(modifier = Modifier.height(60.dp))

        SelectButton(
            leftText = "실내",
            rightText = "실외",
            leftOnClick = { vm.toggleLocation(true) },
            rightOnClick = { vm.toggleLocation(false) },
            isSelectedLeft = vm.isInside
        )

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            contentAlignment = Alignment.Center
        ) {
            if (vm.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(30.dp),
                    color = black,
                    strokeWidth = 3.dp
                )
            } else {
                BottomButton(
                    text = "퀘스트 받기",
                    onClick = {
                        vm.fetchQuestData {
                            onNavigateToQuest()
                        }
                    },
                    enabled = vm.isButtonEnabled
                )
            }
        }
    }
}