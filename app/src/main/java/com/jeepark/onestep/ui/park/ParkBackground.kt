package com.jeepark.onestep.ui.park

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.jeepark.onestep.ui.animal.PixelAnimalRenderer
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// 앱을 켤 때마다 배치 4종 중 하나가 무작위로 골라진다 (배치 데이터는 ParkLayouts.kt)
private val parkVariant = PARK_VARIANTS.indices.random()

// ===== 공원 배경 Canvas =====
// 장면 그리기는 ParkScenery.kt, 말풍선은 SpeechBubble.kt. 여기서는 동물 배치·탭·애니메이션 상태를 다룬다.

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
            drawParkScenery(tier, density)

            // 동물 그리기 (탭한 동물은 점프)
            animals.forEach { placed ->
                val xPx = size.width * placed.placement.x
                val yPx = animalTopY(placed, size.height, pxSz)
                val offsetY = if (placed.animal.id == tappedId) bounceY.value else 0f
                with(PixelAnimalRenderer) {
                    drawSprite(placed.animal.sprite, Offset(xPx, yPx + offsetY), pxSz)
                }
            }
        }

        // 말풍선 오버레이
        val tapped = animals.firstOrNull { it.animal.id == tappedId }
        if (tapped != null && canvasSize.width > 0f) {
            val animalW = tapped.animal.sprite.width * pxSz
            SpeechBubble(
                text             = bubbleText,
                anchorCenterX    = canvasSize.width * tapped.placement.x + animalW / 2f,
                anchorTopY       = animalTopY(tapped, canvasSize.height, pxSz) + bounceY.value,
                containerWidthPx = canvasSize.width,
                alpha            = { bubbleAlpha.value },
                slideY           = { bubbleSlideY.value },
            )
        }
    }
}
