package com.jeepark.onestep.ui.park

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * 동물 머리 위에 뜨는 말풍선.
 *
 * @param anchorCenterX 말풍선 꼬리가 향할 가로 중심(캔버스 px)
 * @param anchorTopY 동물 머리 꼭대기의 세로 위치(캔버스 px). 말풍선은 이보다 위에 놓인다.
 * @param containerWidthPx 캔버스 너비(px). 말풍선이 화면 밖으로 나가지 않게 이 안으로 밀려난다.
 * @param alpha 투명도(0~1). 애니메이션 값이라 함수로 받아, 값이 바뀔 때 말풍선만 다시 그려진다.
 * @param slideY 아래에서 올라오는 애니메이션의 세로 오프셋(px). 위와 같은 이유로 함수로 받는다.
 */
@Composable
internal fun SpeechBubble(
    text: String,
    anchorCenterX: Float,
    anchorTopY: Float,
    containerWidthPx: Float,
    alpha: () -> Float,
    slideY: () -> Float,
) {
    var bubbleWidthPx by remember { mutableStateOf(0) }
    val shape = remember { SpeechBubbleShape() }

    Box(
        modifier = Modifier
            .offset {
                val edge = 8.dp.toPx().toInt()
                val halfW = bubbleWidthPx / 2
                val maxX  = (containerWidthPx.toInt() - bubbleWidthPx - edge).coerceAtLeast(edge)
                val rawX  = anchorCenterX.toInt() - halfW
                val rawY  = (anchorTopY - 70.dp.toPx() + slideY()).toInt()
                IntOffset(
                    // 너비를 재기 전에는 화면 밖에 두어 잘못된 위치가 깜빡이지 않게 한다
                    x = if (bubbleWidthPx == 0) -9999 else rawX.coerceIn(edge, maxX),
                    y = rawY.coerceAtLeast(edge)
                )
            }
            .alpha(alpha())
            .onSizeChanged { bubbleWidthPx = it.width }
            .shadow(elevation = 6.dp, shape = shape)
            .clip(shape)
            .background(Color.White)
            .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 18.dp)
    ) {
        Text(
            text       = text,
            fontSize   = 13.sp,
            color      = Color(0xFF2A2A2A),
            fontWeight = FontWeight.Medium
        )
    }
}

// 말풍선 모양 (아래쪽 꼬리 포함)
private class SpeechBubbleShape(
    private val cornerRadius: Dp = 14.dp,
    private val tailHeight: Dp = 8.dp,
    private val tailWidth: Dp = 14.dp
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val cornerPx  = with(density) { cornerRadius.toPx() }
        val tailHPx   = with(density) { tailHeight.toPx() }
        val tailWPx   = with(density) { tailWidth.toPx() }
        val bubbleH   = size.height - tailHPx
        val tailCx    = size.width / 2f

        val path = Path().apply {
            // 본체 (둥근 사각형)
            addRoundRect(
                RoundRect(
                    rect = Rect(0f, 0f, size.width, bubbleH),
                    cornerRadius = CornerRadius(cornerPx, cornerPx)
                )
            )
            // 꼬리 (아래로 향한 삼각형)
            moveTo(tailCx - tailWPx / 2f, bubbleH)
            lineTo(tailCx, size.height)
            lineTo(tailCx + tailWPx / 2f, bubbleH)
            close()
        }
        return Outline.Generic(path)
    }
}
