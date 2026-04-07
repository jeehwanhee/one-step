package com.jeepark.onestep.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeepark.onestep.ui.theme.red
import com.jeepark.onestep.ui.theme.text_regular_16

@Composable
fun InputWithWarning(
    modifier: Modifier = Modifier,
    warningText: String = "",
    inputComp: @Composable () -> Unit,
) {
    Column(
        modifier=modifier
            .fillMaxWidth()

    ) {
        inputComp()

        Spacer(modifier = Modifier.height(16.dp))

        Text(text= warningText, style = text_regular_16, color = red)
    }
}

@Preview
@Composable
private fun PreviewInputWithWarning() {
    var warningText by remember { mutableStateOf("한글, 영어, 숫자만 가능합니다.") }
    var text by remember { mutableStateOf("") }
    val inputComp = TextInput(
        text = text,
        onValueChange = { text = it },
        placeholder = "이름을 입력하세요"
    )
    InputWithWarning(warningText = warningText, inputComp = { inputComp })
}