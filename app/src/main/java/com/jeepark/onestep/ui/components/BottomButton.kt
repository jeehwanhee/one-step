package com.jeepark.onestep.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults.contentPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeepark.onestep.ui.theme.OneStepTheme
import com.jeepark.onestep.ui.theme.beige2
import com.jeepark.onestep.ui.theme.black
import com.jeepark.onestep.ui.theme.gray1
import com.jeepark.onestep.ui.theme.purple
import com.jeepark.onestep.ui.theme.text_Bold_20
import com.jeepark.onestep.ui.theme.white

@Composable
fun BottomButton (
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    textColor: Color = black,
    containerColor: Color = beige2,
    borderColor: Color = purple,
    borderWidth: Int = 0
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxSize(),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(borderWidth.dp, borderColor),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = textColor,
            disabledContainerColor = gray1,
            disabledContentColor = white
        ),
        contentPadding = PaddingValues(10.dp),
        enabled = enabled,
    ) {
        Text(
            text = text,
            style = text_Bold_20
        )
    }
}

@Preview
@Composable
private fun PrevBottomButton() {
    OneStepTheme {
        BottomButton(text = "예시 버튼", onClick = {})
    }
}