package com.jeepark.onestep.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun StarRating(level: Int, starSize: TextUnit = 16.sp) {
    Row {
        repeat(5) { i ->
            Text("★", fontSize = starSize, color = if (i < level) Color(0xFFF0C030) else Color(0xFFE0D8C8))
        }
    }
}
