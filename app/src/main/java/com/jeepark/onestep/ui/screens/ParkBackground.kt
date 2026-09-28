package com.jeepark.onestep.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.layout.onSizeChanged
import kotlinx.coroutines.launch
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeepark.onestep.util.PixelAnimalRenderer
import kotlinx.coroutines.delay

// ===== 드로잉 헬퍼 =====

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

// 앱을 켤 때마다 배치 4종 중 하나가 무작위로 골라진다 (배치 데이터는 ParkLayouts.kt)
private val parkVariant = PARK_VARIANTS.indices.random()

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
                androidx.compose.ui.geometry.RoundRect(
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

// ===== 공원 배경 Canvas =====

@Composable
internal fun ParkBackground(tier: Int, modifier: Modifier = Modifier) {
    val density = LocalDensity.current
    val animals = remember(tier) { placedAnimals(parkVariant, tier) }
    val pxSz    = with(density) { 3.5.dp.toPx() }

    var canvasSize    by remember { mutableStateOf(Size.Zero) }
    var tappedId      by remember { mutableStateOf<String?>(null) }
    var bubbleText    by remember { mutableStateOf("") }
    var tapTrigger    by remember { mutableStateOf(0) }
    var lastTapTime   by remember { mutableStateOf(0L) }
    var bubbleWidthPx by remember { mutableStateOf(0) }
    val bounceY       = remember { Animatable(0f) }
    val bubbleAlpha   = remember { Animatable(0f) }
    val bubbleSlideY  = remember { Animatable(0f) }
    val bouncePeak    = with(density) { 12.dp.toPx() }
    val slideStartPx  = with(density) { 12.dp.toPx() }

    LaunchedEffect(tapTrigger) {
        if (tapTrigger == 0) return@LaunchedEffect
        val triggered = tapTrigger
        bounceY.snapTo(0f)
        bubbleAlpha.snapTo(0f)
        bubbleSlideY.snapTo(slideStartPx)

        launch {
            bounceY.animateTo(-bouncePeak, animationSpec = tween(120))
            bounceY.animateTo(0f, animationSpec = spring(dampingRatio = 0.5f))
        }
        launch { bubbleSlideY.animateTo(0f, animationSpec = tween(220)) }
        bubbleAlpha.animateTo(1f, animationSpec = tween(220))

        delay(2000)
        if (tapTrigger == triggered) {
            bubbleAlpha.animateTo(0f, animationSpec = tween(180))
            if (tapTrigger == triggered) tappedId = null
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .onGloballyPositioned {
                    canvasSize = Size(it.size.width.toFloat(), it.size.height.toFloat())
                }
                .pointerInput(parkVariant, tier) {
                    detectTapGestures { offset ->
                        val now = System.currentTimeMillis()
                        if (now - lastTapTime < 1000L) return@detectTapGestures
                        val w = size.width.toFloat()
                        val h = size.height.toFloat()
                        animals
                            .firstOrNull { placed ->
                                val animalH = placed.animal.sprite.height * pxSz
                                val animalW = placed.animal.sprite.width * pxSz
                                val xPx = w * placed.placement.x
                                val yPx = animalTopY(placed, h, pxSz)
                                offset.x in xPx..(xPx + animalW) &&
                                offset.y in yPx..(yPx + animalH)
                            }?.let { placed ->
                                lastTapTime = now
                                tappedId    = placed.animal.id
                                bubbleText  = placed.animal.messages.randomOrNull() ?: ""
                                tapTrigger += 1
                            }
                    }
                }
        ) {
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

            // 동물 그리기 (탭한 동물은 점프)
            animals.forEach { placed ->
                val xPx = w * placed.placement.x
                val yPx = animalTopY(placed, h, pxSz)
                val offsetY = if (placed.animal.id == tappedId) bounceY.value else 0f
                with(PixelAnimalRenderer) {
                    drawSprite(placed.animal.sprite, Offset(xPx, yPx + offsetY), pxSz)
                }
            }
        }

        // 말풍선 오버레이
        val tapped = animals.firstOrNull { it.animal.id == tappedId }
        if (tapped != null && canvasSize.width > 0f) {
            val w = canvasSize.width
            val h = canvasSize.height
            val animalW = tapped.animal.sprite.width * pxSz
            val animalCenterX = w * tapped.placement.x + animalW / 2f
            val bubbleAnchorY = animalTopY(tapped, h, pxSz) + bounceY.value
            val bubbleShape   = remember { SpeechBubbleShape() }

            Box(
                modifier = Modifier
                    .offset {
                        val edge = 8.dp.toPx().toInt()
                        val halfW = bubbleWidthPx / 2
                        val maxX  = (w.toInt() - bubbleWidthPx - edge).coerceAtLeast(edge)
                        val rawX  = (animalCenterX.toInt() - halfW)
                        val rawY  = (bubbleAnchorY - 70.dp.toPx() + bubbleSlideY.value).toInt()
                        IntOffset(
                            x = if (bubbleWidthPx == 0) -9999 else rawX.coerceIn(edge, maxX),
                            y = rawY.coerceAtLeast(edge)
                        )
                    }
                    .alpha(bubbleAlpha.value)
                    .onSizeChanged { bubbleWidthPx = it.width }
                    .shadow(elevation = 6.dp, shape = bubbleShape)
                    .clip(bubbleShape)
                    .background(Color.White)
                    .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 18.dp)
            ) {
                Text(
                    text       = bubbleText,
                    fontSize   = 13.sp,
                    color      = Color(0xFF2A2A2A),
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
