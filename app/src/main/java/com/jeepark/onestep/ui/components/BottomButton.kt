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
import com.jeepark.onestep.ui.theme.PrimaryGreen
import com.jeepark.onestep.ui.theme.PrimaryGreenShadow
import com.jeepark.onestep.ui.theme.text_Bold_20

@Composable
fun BottomButton (
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    textColor: Color = Color.White,
    containerColor: Color = PrimaryGreen,
    borderColor: Color = PrimaryGreenShadow,
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
            disabledContainerColor = PrimaryGreen.copy(alpha = 0.4f),
            disabledContentColor = Color.White.copy(alpha = 0.7f)
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