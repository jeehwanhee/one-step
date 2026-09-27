package com.jeepark.onestep.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jeepark.onestep.ui.theme.PrimaryGreenShadow

/**
 * 목업의 하드 오프셋 섀도(예: `0 4px 0 #2A5324`, 블러 없음)를 재현하는 Modifier.
 * Material의 [androidx.compose.ui.draw.shadow]는 블러가 들어간 소프트 섀도라 이 룩을 낼 수 없어 직접 그린다.
 */
fun Modifier.flatShadow(
    shape: Shape = RoundedCornerShape(50),
    color: Color = PrimaryGreenShadow,
    offset: Dp = 4.dp
): Modifier = this.drawBehind {
    val offsetPx = offset.toPx()
    val outline = shape.createOutline(size, layoutDirection, this)
    val path = Path().apply {
        when (outline) {
            is Outline.Rectangle -> addRect(outline.rect)
            is Outline.Rounded -> addRoundRect(outline.roundRect)
            is Outline.Generic -> addPath(outline.path)
        }
    }
    translate(top = offsetPx) {
        drawPath(path = path, color = color)
    }
}
