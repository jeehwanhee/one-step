package com.jeepark.onestep.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeepark.onestep.ui.theme.text_Bold_24

@Composable
fun PlainTextButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    textStyle: TextStyle = text_Bold_24,
    ) {
    TextButton(
        onClick = onClick,
        modifier = modifier
            .width(270.dp)
            .height(60.dp)
    ) {
        Text(text=text, style = textStyle)
    }
}

@Preview
@Composable
private fun PreviewPlainTextButton() {
    PlainTextButton(text="퀘스트 받기", onClick = {})
}