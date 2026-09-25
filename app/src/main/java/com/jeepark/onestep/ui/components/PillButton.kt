package com.jeepark.onestep.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeepark.onestep.ui.theme.MutedText
import com.jeepark.onestep.ui.theme.PrimaryGreen
import com.jeepark.onestep.ui.theme.PrimaryGreenShadow
import com.jeepark.onestep.ui.theme.SecondaryBackground
import com.jeepark.onestep.ui.theme.SecondaryBorder

private val PillShape = RoundedCornerShape(percent = 50)

/** 목업의 "새 퀘스트 받기" 같은 프라이머리 pill 버튼: 진한 그린 배경 + 하드 오프셋 섀도. */
@Composable
fun PrimaryPillButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = PillShape,
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryGreen,
            contentColor = Color.White,
            disabledContainerColor = PrimaryGreen.copy(alpha = 0.4f),
            disabledContentColor = Color.White.copy(alpha = 0.7f)
        ),
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 58.dp)
            .flatShadow(shape = PillShape, color = PrimaryGreenShadow)
    ) {
        Text(text = text, fontSize = 15.sp, fontWeight = FontWeight.Bold)
    }
}

/** 목업의 "발자취"/"진척도"/"포기" 같은 세컨더리 pill 버튼: 연한 크림 배경 + 얇은 보더. */
@Composable
fun SecondaryPillButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = PillShape,
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp
        ),
        border = BorderStroke(1.5.dp, SecondaryBorder),
        colors = ButtonDefaults.buttonColors(
            containerColor = SecondaryBackground,
            contentColor = MutedText,
            disabledContainerColor = SecondaryBackground.copy(alpha = 0.6f),
            disabledContentColor = MutedText.copy(alpha = 0.5f)
        ),
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 58.dp)
    ) {
        Text(text = text, fontSize = 13.5.sp, fontWeight = FontWeight.Medium)
    }
}
