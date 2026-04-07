package com.jeepark.onestep.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.jeepark.onestep.ui.theme.gray2
import com.jeepark.onestep.ui.theme.white

@Composable
fun PagerNavigationButton(
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    // 테두리가 있는 원형 버튼
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(50.dp)
            .clip(CircleShape)
            .border(1.dp, gray2, CircleShape) // 회색 테두리
            .background(white)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = gray2, // 화살표 색상
            modifier = Modifier.size(30.dp)
        )
    }
}