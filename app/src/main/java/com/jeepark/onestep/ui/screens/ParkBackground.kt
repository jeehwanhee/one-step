package com.jeepark.onestep.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.jeepark.onestep.util.BEAR_COLORS
import com.jeepark.onestep.util.BEAR_PIXELS
import com.jeepark.onestep.util.BLUEBIRD_COLORS
import com.jeepark.onestep.util.BLUEBIRD_PIXELS
import com.jeepark.onestep.util.CAT_COLORS
import com.jeepark.onestep.util.CAT_PIXELS
import com.jeepark.onestep.util.CHICK_COLORS
import com.jeepark.onestep.util.CHICK_PIXELS
import com.jeepark.onestep.util.DOG_COLORS
import com.jeepark.onestep.util.DOG_PIXELS
import com.jeepark.onestep.util.DOLPHIN_COLORS
import com.jeepark.onestep.util.DOLPHIN_PIXELS
import com.jeepark.onestep.util.HORSE_COLORS
import com.jeepark.onestep.util.HORSE_PIXELS
import com.jeepark.onestep.util.TURTLE_COLORS
import com.jeepark.onestep.util.TURTLE_PIXELS

// ===== 드로잉 헬퍼 =====

internal fun DrawScope.drawPixelArt(
    pixels: Array<IntArray>,
    colors: List<Color>,
    topLeft: Offset,
    pixelSize: Float
) {
    pixels.forEachIndexed { row, cols ->
        cols.forEachIndexed { col, ci ->
            if (ci > 0 && ci < colors.size) {
                drawRect(
                    color   = colors[ci],
                    topLeft = Offset(topLeft.x + col * pixelSize, topLeft.y + row * pixelSize),
                    size    = Size(pixelSize, pixelSize)
                )
            }
        }
    }
}

internal fun DrawScope.drawTree(centerX: Float, bottomY: Float, density: Density) {
    val trunkW = with(density) { 12.dp.toPx() }
    val trunkH = with(density) { 55.dp.toPx() }
    val leafR  = with(density) { 30.dp.toPx() }
    drawRect(Color(0xFF8B5E3C), topLeft = Offset(centerX - trunkW / 2, bottomY - trunkH), size = Size(trunkW, trunkH))
    drawCircle(Color(0xFF5A9048), leafR,         Offset(centerX, bottomY - trunkH - leafR * 0.55f))
    drawCircle(Color(0xFF4A8038), leafR * 0.85f, Offset(centerX - leafR * 0.65f, bottomY - trunkH - leafR * 0.15f))
    drawCircle(Color(0xFF4A8038), leafR * 0.85f, Offset(centerX + leafR * 0.65f, bottomY - trunkH - leafR * 0.15f))
}

internal fun DrawScope.drawCloud(center: Offset, radius: Float) {
    val c = Color.White.copy(alpha = 0.88f)
    drawCircle(c, radius,         center)
    drawCircle(c, radius * 0.72f, Offset(center.x + radius * 0.88f, center.y + radius * 0.12f))
    drawCircle(c, radius * 0.72f, Offset(center.x - radius * 0.80f, center.y + radius * 0.18f))
}

internal fun DrawScope.drawFence(y: Float, screenW: Float, density: Density) {
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

internal fun tierSkyColor(tier: Int): Color = when (tier) {
    0    -> Color(0xFFB0CCE8)
    1    -> Color(0xFFA4C6E4)
    2    -> Color(0xFF98C0E0)
    3    -> Color(0xFF8CBADC)
    4    -> Color(0xFF70A8D4)
    5    -> Color(0xFF5484B0)
    else -> Color(0xFFF5E5A0)
}

// ===== 동물 배치 variant =====
// groundY(fraction, animalH): fraction 0.0=뒤, 1.0=앞
// skyY(fraction, animalH):    fraction 0.0=위, 1.0=아래

private fun DrawScope.drawAnimalsVariant0(
    tier: Int,
    w: Float,
    pxSz: Float,
    groundY: (Float, Float) -> Float,
    skyY: (Float, Float) -> Float
) {
    val chickH    = CHICK_PIXELS.size    * pxSz
    val turtleH   = TURTLE_PIXELS.size   * pxSz
    val catH      = CAT_PIXELS.size      * pxSz
    val dogH      = DOG_PIXELS.size      * pxSz
    val bluebirdH = BLUEBIRD_PIXELS.size * pxSz
    val bearH     = BEAR_PIXELS.size     * pxSz
    val horseH    = HORSE_PIXELS.size    * pxSz
    val dolphinH  = DOLPHIN_PIXELS.size  * pxSz

    drawPixelArt(CHICK_PIXELS,    CHICK_COLORS,    Offset(w * 0.68f, groundY(0.42f, chickH)),    pxSz)
    if (tier >= 1) drawPixelArt(TURTLE_PIXELS,   TURTLE_COLORS,   Offset(w * 0.06f, groundY(0.64f, turtleH)),  pxSz)
    if (tier >= 2) drawPixelArt(CAT_PIXELS,      CAT_COLORS,      Offset(w * 0.10f, groundY(0.15f, catH)),     pxSz)
    if (tier >= 3) drawPixelArt(DOG_PIXELS,      DOG_COLORS,      Offset(w * 0.69f, groundY(0.75f, dogH)),     pxSz)
    if (tier >= 4) drawPixelArt(BLUEBIRD_PIXELS, BLUEBIRD_COLORS, Offset(w * 0.25f, skyY(0.40f,  bluebirdH)), pxSz)
    if (tier >= 5) drawPixelArt(BEAR_PIXELS,     BEAR_COLORS,     Offset(w * 0.40f, groundY(0.90f, bearH)),   pxSz)
    if (tier >= 6) drawPixelArt(HORSE_PIXELS,    HORSE_COLORS,    Offset(w * 0.52f, groundY(0f,   horseH)),   pxSz)
    if (tier >= 7) drawPixelArt(DOLPHIN_PIXELS,  DOLPHIN_COLORS,  Offset(w * 0.12f, groundY(0.45f, dolphinH)),pxSz)
}

private fun DrawScope.drawAnimalsVariant1(
    tier: Int,
    w: Float,
    pxSz: Float,
    groundY: (Float, Float) -> Float,
    skyY: (Float, Float) -> Float
) {
    val chickH    = CHICK_PIXELS.size    * pxSz
    val turtleH   = TURTLE_PIXELS.size   * pxSz
    val catH      = CAT_PIXELS.size      * pxSz
    val dogH      = DOG_PIXELS.size      * pxSz
    val bluebirdH = BLUEBIRD_PIXELS.size * pxSz
    val bearH     = BEAR_PIXELS.size     * pxSz
    val horseH    = HORSE_PIXELS.size    * pxSz
    val dolphinH  = DOLPHIN_PIXELS.size  * pxSz

    drawPixelArt(CHICK_PIXELS,    CHICK_COLORS,    Offset(w * 0.05f, groundY(0.85f, chickH)),    pxSz)
    if (tier >= 1) drawPixelArt(TURTLE_PIXELS,   TURTLE_COLORS,   Offset(w * 0.42f, groundY(0.80f, turtleH)),  pxSz)
    if (tier >= 2) drawPixelArt(CAT_PIXELS,      CAT_COLORS,      Offset(w * 0.68f, groundY(0.40f, catH)),     pxSz)
    if (tier >= 3) drawPixelArt(DOG_PIXELS,      DOG_COLORS,      Offset(w * 0.72f, groundY(0.85f, dogH)),     pxSz)
    if (tier >= 4) drawPixelArt(BLUEBIRD_PIXELS, BLUEBIRD_COLORS, Offset(w * 0.65f, skyY(0.15f,  bluebirdH)), pxSz)
    if (tier >= 5) drawPixelArt(BEAR_PIXELS,     BEAR_COLORS,     Offset(w * 0.10f, groundY(0.30f, bearH)),   pxSz)
    if (tier >= 6) drawPixelArt(HORSE_PIXELS,    HORSE_COLORS,    Offset(w * 0.20f, groundY(0.75f, horseH)),  pxSz)
    if (tier >= 7) drawPixelArt(DOLPHIN_PIXELS,  DOLPHIN_COLORS,  Offset(w * 0.12f, skyY(0.35f,  dolphinH)), pxSz)
}

private fun DrawScope.drawAnimalsVariant2(
    tier: Int,
    w: Float,
    pxSz: Float,
    groundY: (Float, Float) -> Float,
    skyY: (Float, Float) -> Float
) {
    val chickH    = CHICK_PIXELS.size    * pxSz
    val turtleH   = TURTLE_PIXELS.size   * pxSz
    val catH      = CAT_PIXELS.size      * pxSz
    val dogH      = DOG_PIXELS.size      * pxSz
    val bluebirdH = BLUEBIRD_PIXELS.size * pxSz
    val bearH     = BEAR_PIXELS.size     * pxSz
    val horseH    = HORSE_PIXELS.size    * pxSz
    val dolphinH  = DOLPHIN_PIXELS.size  * pxSz

    drawPixelArt(CHICK_PIXELS,    CHICK_COLORS,    Offset(w * 0.45f, groundY(0.88f, chickH)),    pxSz)
    if (tier >= 1) drawPixelArt(TURTLE_PIXELS,   TURTLE_COLORS,   Offset(w * 0.62f, groundY(0.55f, turtleH)),  pxSz)
    if (tier >= 2) drawPixelArt(CAT_PIXELS,      CAT_COLORS,      Offset(w * 0.30f, groundY(0.20f, catH)),     pxSz)
    if (tier >= 3) drawPixelArt(DOG_PIXELS,      DOG_COLORS,      Offset(w * 0.08f, groundY(0.80f, dogH)),     pxSz)
    if (tier >= 4) drawPixelArt(BLUEBIRD_PIXELS, BLUEBIRD_COLORS, Offset(w * 0.70f, skyY(0.30f,  bluebirdH)), pxSz)
    if (tier >= 5) drawPixelArt(BEAR_PIXELS,     BEAR_COLORS,     Offset(w * 0.55f, groundY(0.10f, bearH)),   pxSz)
    if (tier >= 6) drawPixelArt(HORSE_PIXELS,    HORSE_COLORS,    Offset(w * 0.35f, groundY(0.70f, horseH)),  pxSz)
    if (tier >= 7) drawPixelArt(DOLPHIN_PIXELS,  DOLPHIN_COLORS,  Offset(w * 0.80f, skyY(0.20f,  dolphinH)), pxSz)
}

private fun DrawScope.drawAnimalsVariant3(
    tier: Int,
    w: Float,
    pxSz: Float,
    groundY: (Float, Float) -> Float,
    skyY: (Float, Float) -> Float
) {
    val chickH    = CHICK_PIXELS.size    * pxSz
    val turtleH   = TURTLE_PIXELS.size   * pxSz
    val catH      = CAT_PIXELS.size      * pxSz
    val dogH      = DOG_PIXELS.size      * pxSz
    val bluebirdH = BLUEBIRD_PIXELS.size * pxSz
    val bearH     = BEAR_PIXELS.size     * pxSz
    val horseH    = HORSE_PIXELS.size    * pxSz
    val dolphinH  = DOLPHIN_PIXELS.size  * pxSz

    drawPixelArt(CHICK_PIXELS,    CHICK_COLORS,    Offset(w * 0.78f, groundY(0.70f, chickH)),    pxSz)
    if (tier >= 1) drawPixelArt(TURTLE_PIXELS,   TURTLE_COLORS,   Offset(w * 0.20f, groundY(0.90f, turtleH)),  pxSz)
    if (tier >= 2) drawPixelArt(CAT_PIXELS,      CAT_COLORS,      Offset(w * 0.58f, groundY(0.10f, catH)),     pxSz)
    if (tier >= 3) drawPixelArt(DOG_PIXELS,      DOG_COLORS,      Offset(w * 0.42f, groundY(0.82f, dogH)),     pxSz)
    if (tier >= 4) drawPixelArt(BLUEBIRD_PIXELS, BLUEBIRD_COLORS, Offset(w * 0.15f, skyY(0.25f,  bluebirdH)), pxSz)
    if (tier >= 5) drawPixelArt(BEAR_PIXELS,     BEAR_COLORS,     Offset(w * 0.72f, groundY(0.50f, bearH)),   pxSz)
    if (tier >= 6) drawPixelArt(HORSE_PIXELS,    HORSE_COLORS,    Offset(w * 0.05f, groundY(0.60f, horseH)),  pxSz)
    if (tier >= 7) drawPixelArt(DOLPHIN_PIXELS,  DOLPHIN_COLORS,  Offset(w * 0.55f, skyY(0.10f,  dolphinH)), pxSz)
}

private val parkVariant = (0..3).random()

// ===== 공원 배경 Canvas =====

@Composable
internal fun ParkBackground(tier: Int, modifier: Modifier = Modifier) {
    val density = LocalDensity.current
    val variant = parkVariant

    Canvas(modifier = modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height
        val skyH    = h * 0.35f
        val grass1  = h * 0.45f
        val grass2  = h * 0.62f
        val groundY = h * 0.58f
        val pxSz    = with(density) { 3.5.dp.toPx() }

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

        // 동물 배치 zones
        val skyZoneBottom    = h / 3f
        val groundZoneTop    = h / 3f
        val groundZoneBottom = h * 0.88f

        fun groundY(fraction: Float, animalH: Float): Float {
            val range = (groundZoneBottom - animalH - groundZoneTop).coerceAtLeast(0f)
            return groundZoneTop + fraction * range
        }
        fun skyY(fraction: Float, animalH: Float): Float {
            val range = (skyZoneBottom - animalH).coerceAtLeast(0f)
            return fraction * range
        }

        // 랜덤 배치 variant
        when (variant) {
            0 -> drawAnimalsVariant0(tier, w, pxSz, ::groundY, ::skyY)
            1 -> drawAnimalsVariant1(tier, w, pxSz, ::groundY, ::skyY)
            2 -> drawAnimalsVariant2(tier, w, pxSz, ::groundY, ::skyY)
            3 -> drawAnimalsVariant3(tier, w, pxSz, ::groundY, ::skyY)
        }
    }
}
