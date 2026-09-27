package com.jeepark.onestep.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeepark.onestep.data.model.IsolatedRecord
import com.jeepark.onestep.ui.theme.CardSurface

private val GRAPH_LINE  = Color(0xFF5A9848)
private val GRAPH_FILL  = Color(0x335A9848)
private val GRAPH_GRID  = Color(0xFFE6E0D0)
private val GRAPH_DOT   = Color(0xFF3F7A36)

@Composable
fun RecoveryGraph(
    history: List<IsolatedRecord>,
    modifier: Modifier = Modifier
) {
    // 회복도 = 100 - isolated, 시간순 정렬
    val sorted = history.sortedBy { it.recordedAt }
    val recoveries = sorted.map { (100 - it.score).coerceIn(0, 100) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(CardSurface)
            .padding(horizontal = 16.dp, vertical = 14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text       = "회복도",
                fontSize   = 14.sp,
                fontWeight = FontWeight.Bold,
                color      = Color(0xFF3A3A3A)
            )
        }

        Spacer(Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
        ) {
            if (recoveries.size < 2) {
                Box(
                    modifier         = Modifier.fillMaxWidth().height(140.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text     = if (recoveries.isEmpty())
                            "아직 기록이 없어요"
                        else
                            "기록이 쌓이면 그래프가 그려져요",
                        fontSize = 12.sp,
                        color    = Color(0xFFAAAAAA)
                    )
                }
            } else {
                Canvas(modifier = Modifier.fillMaxWidth().height(140.dp)) {
                    val padL = 4f
                    val padR = 4f
                    val padT = 8f
                    val padB = 8f
                    val w = size.width  - padL - padR
                    val h = size.height - padT - padB

                    // 가이드라인 (0/50/100)
                    val dash = PathEffect.dashPathEffect(floatArrayOf(8f, 8f), 0f)
                    listOf(0f, 0.5f, 1f).forEach { f ->
                        val y = padT + h * f
                        drawLine(
                            color       = GRAPH_GRID,
                            start       = Offset(padL, y),
                            end         = Offset(padL + w, y),
                            strokeWidth = 1.5f,
                            pathEffect  = dash
                        )
                    }

                    val n = recoveries.size
                    val stepX = if (n > 1) w / (n - 1) else 0f
                    val points = recoveries.mapIndexed { i, v ->
                        val x = padL + stepX * i
                        val y = padT + h * (1f - v / 100f)
                        Offset(x, y)
                    }

                    // 영역 채우기
                    val fillPath = Path().apply {
                        moveTo(points.first().x, padT + h)
                        points.forEach { lineTo(it.x, it.y) }
                        lineTo(points.last().x, padT + h)
                        close()
                    }
                    drawPath(path = fillPath, color = GRAPH_FILL)

                    // 라인
                    val linePath = Path().apply {
                        moveTo(points.first().x, points.first().y)
                        for (i in 1 until points.size) lineTo(points[i].x, points[i].y)
                    }
                    drawPath(
                        path  = linePath,
                        color = GRAPH_LINE,
                        style = Stroke(width = 4f)
                    )

                    // 점
                    points.forEach { p ->
                        drawCircle(color = Color.White, radius = 5f, center = p)
                        drawCircle(color = GRAPH_DOT,   radius = 3.5f, center = p)
                    }
                }
            }
        }
    }
}
