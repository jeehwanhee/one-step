package com.jeepark.onestep.ui.park

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp

// 공원 장면(하늘·잔디·나무·울타리·연못·꽃). 동물은 ParkBackground가 이 위에 그린다.

/**
 * 티어에 맞춰 공원 장면을 그린다. 티어가 오를수록 요소가 늘어난다:
 * 왼쪽 나무(1+) · 오른쪽 나무·울타리(2+) · 해·구름(3+) · 연못(4+) · 꽃(5+).
 */
internal fun DrawScope.drawParkScenery(tier: Int, density: Density) {
    val w = size.width
    val h = size.height
    val skyH    = h * 0.35f
    val grass1  = h * 0.45f
    val grass2  = h * 0.62f
    val groundY = h * 0.58f

    // 하늘
    drawRect(tierSkyColor(tier), topLeft = Offset.Zero, size = Size(w, skyH))

    // 잔디 3겹
    drawRect(Color(0xFF6EB04A), topLeft = Offset(0f, skyH),  size = Size(w, grass1 - skyH))
    drawRect(Color(0xFF56A038), topLeft = Offset(0f, grass1), size = Size(w, grass2 - grass1))
    drawRect(Color(0xFF468028), topLeft = Offset(0f, grass2), size = Size(w, h - grass2))

    // 태양 (티어 3+)
    if (tier >= 3) {
        drawCircle(Color(0xFFF8E840), with(density) { 26.dp.toPx() }, Offset(w * 0.83f, skyH * 0.32f))
    }

    // 구름 (티어 3+)
    if (tier >= 3) {
        drawCloud(Offset(w * 0.20f, skyH * 0.22f), with(density) { 22.dp.toPx() })
        drawCloud(Offset(w * 0.58f, skyH * 0.42f), with(density) { 16.dp.toPx() })
    }

    // 왼쪽 나무 (티어 1+)
    if (tier >= 1) drawTree(w * 0.20f, groundY, density)

    // 오른쪽 나무 (티어 2+)
    if (tier >= 2) drawTree(w * 0.80f, groundY, density)

    // 울타리 (티어 2+)
    if (tier >= 2) drawFence(groundY + with(density) { 4.dp.toPx() }, w, density)

    // 연못 (티어 4+)
    if (tier >= 4) {
        val cx = w * 0.28f
        val cy = groundY + with(density) { 16.dp.toPx() }
        val rx = with(density) { 70.dp.toPx() }
        val ry = with(density) { 22.dp.toPx() }
        drawOval(Color(0xFF70B8E0), topLeft = Offset(cx - rx, cy - ry), size = Size(rx * 2, ry * 2))
        drawOval(Color(0xFF88C8F0), topLeft = Offset(cx - rx * 0.7f, cy - ry * 0.6f), size = Size(rx * 1.4f, ry * 1.2f))
    }

    // 꽃 (티어 5+)
    if (tier >= 5) {
        val flowerR = with(density) { 5.dp.toPx() }
        val flowers = listOf(
            Offset(w * 0.10f, groundY - with(density) { 3.dp.toPx() }) to Color(0xFFE85858),
            Offset(w * 0.16f, groundY + with(density) { 7.dp.toPx() }) to Color(0xFFF09840),
            Offset(w * 0.36f, groundY + with(density) { 4.dp.toPx() }) to Color(0xFFD04878),
            Offset(w * 0.63f, groundY + with(density) { 3.dp.toPx() }) to Color(0xFFE85858),
            Offset(w * 0.87f, groundY - with(density) { 2.dp.toPx() }) to Color(0xFFF09840),
            Offset(w * 0.92f, groundY + with(density) { 8.dp.toPx() }) to Color(0xFFD04878),
        )
        flowers.forEach { (pos, color) -> drawCircle(color, flowerR, pos) }
    }
}

private fun DrawScope.drawTree(centerX: Float, bottomY: Float, density: Density) {
    val trunkW = with(density) { 12.dp.toPx() }
    val trunkH = with(density) { 55.dp.toPx() }
    val leafR  = with(density) { 30.dp.toPx() }
    drawRect(Color(0xFF8B5E3C), topLeft = Offset(centerX - trunkW / 2, bottomY - trunkH), size = Size(trunkW, trunkH))
    drawCircle(Color(0xFF5A9048), leafR,         Offset(centerX, bottomY - trunkH - leafR * 0.55f))
    drawCircle(Color(0xFF4A8038), leafR * 0.85f, Offset(centerX - leafR * 0.65f, bottomY - trunkH - leafR * 0.15f))
    drawCircle(Color(0xFF4A8038), leafR * 0.85f, Offset(centerX + leafR * 0.65f, bottomY - trunkH - leafR * 0.15f))
}

private fun DrawScope.drawCloud(center: Offset, radius: Float) {
    val c = Color.White.copy(alpha = 0.88f)
    drawCircle(c, radius,         center)
    drawCircle(c, radius * 0.72f, Offset(center.x + radius * 0.88f, center.y + radius * 0.12f))
    drawCircle(c, radius * 0.72f, Offset(center.x - radius * 0.80f, center.y + radius * 0.18f))
}

private fun DrawScope.drawFence(y: Float, screenW: Float, density: Density) {
    val postW   = with(density) { 5.dp.toPx() }
    val postH   = with(density) { 22.dp.toPx() }
    val spacing = with(density) { 22.dp.toPx() }
    val railH   = with(density) { 2.dp.toPx() }
    val color   = Color(0xFFD4B880)
    var x = 0f
    while (x < screenW) {
        drawRect(color, topLeft = Offset(x, y - postH), size = Size(postW, postH))
        x += spacing
    }
    drawRect(color, topLeft = Offset(0f, y - postH * 0.72f), size = Size(screenW, railH))
    drawRect(color, topLeft = Offset(0f, y - postH * 0.30f), size = Size(screenW, railH))
}

/** 티어 0~5는 티어가 오를수록 짙은 파란 하늘, 그 이상은 옅은 노란 하늘. */
private fun tierSkyColor(tier: Int): Color = when (tier) {
    0    -> Color(0xFFB0CCE8)
    1    -> Color(0xFFA4C6E4)
    2    -> Color(0xFF98C0E0)
    3    -> Color(0xFF8CBADC)
    4    -> Color(0xFF70A8D4)
    5    -> Color(0xFF5484B0)
    else -> Color(0xFFF5E5A0)
}
