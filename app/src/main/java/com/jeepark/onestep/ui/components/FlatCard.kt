package com.jeepark.onestep.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jeepark.onestep.ui.theme.CreamSurface
import com.jeepark.onestep.ui.theme.SecondaryBorder

/**
 * 목업의 카드 룩(모든 모서리 동일 라운드 + 하드 오프셋 섀도)을 재사용하는 래퍼.
 * 스테이지 카드, 다이얼로그, 동물 프로필 카드 등에서 공통으로 사용.
 */
@Composable
fun FlatCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = CreamSurface,
    cornerRadius: Dp = 22.dp,
    shadowColor: Color = SecondaryBorder,
    shadowOffset: Dp = 3.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    val shape = RoundedCornerShape(cornerRadius)
    Column(
        modifier = modifier
            .flatShadow(shape = shape, color = shadowColor, offset = shadowOffset)
            .clip(shape)
            .background(backgroundColor),
        content = content
    )
}
