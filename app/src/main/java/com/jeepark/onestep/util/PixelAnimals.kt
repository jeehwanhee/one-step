package com.jeepark.onestep.util

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope

// =============================================
// 동물 순서: 0병아리 1거북이 2고양이 3강아지 4파랑새 5곰 6말 7돌고래
// 색상 인덱스: 0=투명, 1=본체, 2=어두운중간, 3=그림자(외곽), 4=포인트색1, 5=포인트색2, 6=하이라이트
// =============================================

// 0: 병아리 (12x12)
val CHICK_COLORS = listOf(
    Color.Transparent,
    Color(0xFFFFD600), // 1: 밝은 노랑
    Color(0xFFC8A000), // 2: 어두운 노랑
    Color(0xFF866A00), // 3: 그림자 노랑
    Color(0xFFE07018), // 4: 주황 (부리, 발)
    Color(0xFFFFF080), // 5: 밝은 노랑 하이라이트
    Color(0xFFB85010), // 6: 어두운 주황 (발 그림자)
)
val CHICK_PIXELS = arrayOf(
    intArrayOf(0,0,0,3,3,3,3,0,0,0,0,0),
    intArrayOf(0,0,3,1,1,1,1,3,0,0,0,0),
    intArrayOf(0,3,1,5,1,1,1,1,3,0,0,0),
    intArrayOf(0,3,1,1,2,1,1,4,3,0,0,0),
    intArrayOf(0,3,1,1,1,1,1,4,3,0,0,0),
    intArrayOf(0,3,1,1,1,1,1,1,1,3,0,0),
    intArrayOf(0,3,2,1,1,1,1,1,2,3,0,0),
    intArrayOf(0,0,3,1,1,1,1,1,1,3,0,0),
    intArrayOf(0,0,3,3,1,1,1,3,3,0,0,0),
    intArrayOf(0,0,0,3,1,1,1,3,0,0,0,0),
    intArrayOf(0,0,4,4,3,0,3,4,4,0,0,0),
    intArrayOf(0,0,6,0,0,0,0,0,6,0,0,0),
)

// 1: 거북이 (12x12)
val TURTLE_COLORS = listOf(
    Color.Transparent,
    Color(0xFF58A030), // 1: 초록 몸통
    Color(0xFF286A10), // 2: 어두운 초록
    Color(0xFF1A4008), // 3: 그림자 초록
    Color(0xFFC4A830), // 4: 등껍질 황갈색
    Color(0xFF8A7020), // 5: 어두운 등껍질
    Color(0xFFE8D080), // 6: 밝은 등껍질
)
val TURTLE_PIXELS = arrayOf(
    intArrayOf(0,0,0,0,3,3,0,0,0,0,0,0),
    intArrayOf(0,0,0,3,1,1,3,0,0,0,0,0),
    intArrayOf(0,0,0,2,1,1,2,0,0,0,0,0),
    intArrayOf(0,0,3,4,4,4,4,3,0,0,0,0),
    intArrayOf(0,3,4,6,5,6,5,4,3,0,0,0),
    intArrayOf(3,1,4,5,6,5,6,4,1,3,0,0),
    intArrayOf(3,1,4,6,5,6,5,6,4,1,3,0),
    intArrayOf(3,1,4,5,4,5,4,5,4,1,3,0),
    intArrayOf(0,3,4,4,4,4,4,4,3,0,0,0),
    intArrayOf(0,3,1,2,0,1,1,0,2,3,0,0),
    intArrayOf(0,3,2,1,0,0,0,0,1,3,0,0),
    intArrayOf(0,0,3,2,0,0,0,0,2,3,0,0),
)

// 2: 고양이 (10x14)
val CAT_COLORS = listOf(
    Color.Transparent,
    Color(0xFFF0A060), // 1: 주황
    Color(0xFFC87040), // 2: 어두운 주황
    Color(0xFF8A4020), // 3: 그림자
    Color(0xFFFFFFFF), // 4: 흰색 (귀 안쪽, 주둥이)
    Color(0xFFF8B0C0), // 5: 핑크 코
    Color(0xFFF8C8A0), // 6: 밝은 주황 하이라이트
)
val CAT_PIXELS = arrayOf(
    intArrayOf(3,3,0,0,0,0,0,3,3,0),
    intArrayOf(3,1,3,0,0,0,3,1,3,0),
    intArrayOf(3,1,4,3,0,3,4,1,3,0),
    intArrayOf(0,3,1,1,3,1,1,3,0,0),
    intArrayOf(0,3,1,6,1,1,6,1,3,0),
    intArrayOf(0,3,1,2,1,5,1,1,3,0),
    intArrayOf(0,3,1,1,4,4,1,1,3,0),
    intArrayOf(0,0,3,1,1,1,1,3,0,0),
    intArrayOf(0,0,3,1,1,1,1,3,0,0),
    intArrayOf(0,3,1,1,1,1,1,1,3,0),
    intArrayOf(0,3,1,2,1,1,2,1,3,0),
    intArrayOf(0,3,2,1,0,0,1,2,3,0),
    intArrayOf(0,3,1,2,0,0,2,1,3,0),
    intArrayOf(0,0,3,2,0,0,2,3,0,0),
)

// 3: 강아지 (12x12)
val DOG_COLORS = listOf(
    Color.Transparent,
    Color(0xFFF0C870), // 1: 황갈색
    Color(0xFFC89840), // 2: 어두운 황갈색
    Color(0xFF8A6020), // 3: 그림자
    Color(0xFFFFFFFF), // 4: 흰색 주둥이
    Color(0xFFF08080), // 5: 핑크 코
    Color(0xFFFFF0B0), // 6: 밝은 하이라이트
)
val DOG_PIXELS = arrayOf(
    intArrayOf(0,0,0,3,3,3,3,0,0,0,0,0),
    intArrayOf(3,0,3,1,6,6,1,3,0,3,0,0),
    intArrayOf(3,0,3,1,2,2,1,3,0,3,0,0),
    intArrayOf(3,0,3,1,1,1,1,3,0,3,0,0),
    intArrayOf(3,2,3,1,4,4,1,3,2,3,0,0),
    intArrayOf(0,3,1,1,4,5,4,1,3,0,0,0),
    intArrayOf(0,3,2,1,1,1,1,2,3,0,0,0),
    intArrayOf(0,0,3,1,1,1,1,3,0,0,0,0),
    intArrayOf(0,3,1,1,1,1,1,1,3,0,0,0),
    intArrayOf(0,3,2,1,1,1,1,2,3,0,0,0),
    intArrayOf(0,3,1,1,0,0,1,1,3,0,0,0),
    intArrayOf(0,0,3,2,0,0,2,3,0,0,0,0),
)

// 4: 파랑새 (14x12)
val BLUEBIRD_COLORS = listOf(
    Color.Transparent,
    Color(0xFF2090E0), // 1: 파랑
    Color(0xFF0060B0), // 2: 어두운 파랑
    Color(0xFF003880), // 3: 그림자
    Color(0xFFFFFFFF), // 4: 흰색 배
    Color(0xFFF0C020), // 5: 노랑 부리/발
    Color(0xFF80C4F0), // 6: 밝은 파랑 하이라이트
)
val BLUEBIRD_PIXELS = arrayOf(
    intArrayOf(0,0,0,3,3,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,3,1,1,3,0,0,0,0,0,0,0,0),
    intArrayOf(0,3,1,6,1,1,3,0,0,0,0,0,0,0),
    intArrayOf(0,3,1,1,1,5,5,3,0,0,0,0,0,0),
    intArrayOf(3,1,1,1,4,4,5,5,3,0,0,0,0,0),
    intArrayOf(3,1,2,1,4,4,4,1,1,3,0,0,3,3),
    intArrayOf(3,1,1,1,4,4,1,1,1,1,3,3,1,2),
    intArrayOf(3,1,2,1,1,1,1,1,1,2,1,2,3,0),
    intArrayOf(0,3,1,1,1,1,1,1,3,0,3,0,0,0),
    intArrayOf(0,0,3,2,1,1,2,3,0,0,0,0,0,0),
    intArrayOf(0,0,5,5,3,0,3,5,0,0,0,0,0,0),
    intArrayOf(0,0,5,0,0,0,0,5,0,0,0,0,0,0),
)

// 5: 곰 (10x14)
val BEAR_COLORS = listOf(
    Color.Transparent,
    Color(0xFFD0A070), // 1: 갈색
    Color(0xFF9A7040), // 2: 어두운 갈색
    Color(0xFF6A4820), // 3: 그림자
    Color(0xFFE8C090), // 4: 밝은 갈색 (배, 귀 안쪽)
    Color(0xFFF0A0A0), // 5: 핑크 코
    Color(0xFFEED0B0), // 6: 하이라이트
)
val BEAR_PIXELS = arrayOf(
    intArrayOf(0,3,3,0,0,0,3,3,0,0),
    intArrayOf(3,4,4,3,0,3,4,4,3,0),
    intArrayOf(3,1,1,1,3,1,1,1,3,0),
    intArrayOf(3,1,6,1,1,1,6,1,3,0),
    intArrayOf(3,1,1,2,1,2,1,1,3,0),
    intArrayOf(3,1,1,4,5,4,1,1,3,0),
    intArrayOf(0,3,1,4,4,4,1,3,0,0),
    intArrayOf(0,3,1,1,1,1,1,3,0,0),
    intArrayOf(0,3,1,1,1,1,1,3,0,0),
    intArrayOf(3,1,1,4,4,4,1,1,3,0),
    intArrayOf(3,1,4,4,4,4,4,1,3,0),
    intArrayOf(3,2,1,1,0,0,1,1,2,3),
    intArrayOf(3,1,2,1,0,0,1,2,1,3),
    intArrayOf(0,3,2,3,0,0,3,2,3,0),
)

// 6: 말 (14x12)
val HORSE_COLORS = listOf(
    Color.Transparent,
    Color(0xFFC08040), // 1: 갈색
    Color(0xFF8A5020), // 2: 어두운 갈색
    Color(0xFF5A3010), // 3: 그림자
    Color(0xFFF0F0E0), // 4: 흰색/크림 (갈기, 코 흰색)
    Color(0xFF3A2010), // 5: 진한 갈색 (발굽)
    Color(0xFFE0A060), // 6: 밝은 갈색 하이라이트
)
val HORSE_PIXELS = arrayOf(
    intArrayOf(0,0,3,4,4,3,0,0,0,0,0,0,0,0),
    intArrayOf(0,3,4,4,4,4,3,0,0,0,0,0,0,0),
    intArrayOf(3,4,1,1,1,1,1,3,0,0,0,0,0,0),
    intArrayOf(3,4,1,2,1,1,1,1,3,0,0,0,0,0),
    intArrayOf(3,4,1,1,1,4,1,1,3,0,0,0,0,0),
    intArrayOf(0,3,1,1,1,1,1,1,1,3,0,0,0,0),
    intArrayOf(0,0,3,2,1,1,1,1,1,1,3,0,0,3),
    intArrayOf(0,0,3,1,1,1,1,1,1,2,1,2,3,3),
    intArrayOf(0,0,0,3,2,1,1,1,3,0,0,3,2,3),
    intArrayOf(0,0,3,1,1,0,3,1,1,3,0,0,0,0),
    intArrayOf(0,0,3,2,1,0,3,2,1,3,0,0,0,0),
    intArrayOf(0,0,0,5,5,0,0,5,5,0,0,0,0,0),
)

// 7: 돌고래 (14x9)
val DOLPHIN_COLORS = listOf(
    Color.Transparent,
    Color(0xFF5898C8), // 1: 청회색
    Color(0xFF2860A0), // 2: 어두운 청회색
    Color(0xFF183870), // 3: 그림자
    Color(0xFFD0E8F8), // 4: 밝은 배색
    Color(0xFFA8C8E8), // 5: 중간 배색
    Color(0xFF88B8E8), // 6: 하이라이트
)
val DOLPHIN_PIXELS = arrayOf(
    intArrayOf(0,0,0,3,3,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,3,1,1,3,0,0,0,0,0,0,0,0),
    intArrayOf(0,3,1,6,1,1,3,3,3,3,3,3,0,0),
    intArrayOf(3,1,1,4,4,1,1,1,1,1,2,1,3,0),
    intArrayOf(3,1,4,4,4,4,1,1,1,2,1,2,3,0),
    intArrayOf(0,3,1,5,4,3,1,1,3,0,3,3,0,0),
    intArrayOf(0,0,3,1,3,3,3,3,0,0,0,0,0,0),
    intArrayOf(0,0,3,1,0,0,0,0,0,0,0,0,0,0),
    intArrayOf(0,0,0,3,0,0,0,0,0,0,0,0,0,0),
)

// =============================================
// 공통 렌더러
// =============================================

private val allPixels = listOf(
    CHICK_PIXELS, TURTLE_PIXELS, CAT_PIXELS, DOG_PIXELS,
    BLUEBIRD_PIXELS, BEAR_PIXELS, HORSE_PIXELS, DOLPHIN_PIXELS
)
private val allColors = listOf(
    CHICK_COLORS, TURTLE_COLORS, CAT_COLORS, DOG_COLORS,
    BLUEBIRD_COLORS, BEAR_COLORS, HORSE_COLORS, DOLPHIN_COLORS
)

val ANIMAL_NAMES = listOf("병아리", "거북이", "고양이", "강아지", "파랑새", "곰", "말", "돌고래")

object PixelAnimalRenderer {

    fun DrawScope.drawAnimal(index: Int, topLeft: Offset, pixelSize: Float) {
        val pixels = allPixels[index]
        val colors = allColors[index]
        pixels.forEachIndexed { r, row ->
            row.forEachIndexed { c, ci ->
                if (ci > 0 && ci < colors.size) {
                    drawRect(
                        color   = colors[ci],
                        topLeft = Offset(topLeft.x + c * pixelSize, topLeft.y + r * pixelSize),
                        size    = Size(pixelSize, pixelSize)
                    )
                }
            }
        }
    }

    fun DrawScope.drawAnimalInBox(index: Int, topLeft: Offset, boxSize: Float) {
        val pixels = allPixels[index]
        val cols   = pixels[0].size
        val rows   = pixels.size
        val pixSz  = minOf(boxSize / cols, boxSize / rows)
        val startX = topLeft.x + (boxSize - cols * pixSz) / 2f
        val startY = topLeft.y + (boxSize - rows * pixSz) / 2f
        drawAnimal(index, Offset(startX, startY), pixSz)
    }

    fun pixelWidth(index: Int)  = allPixels[index][0].size
    fun pixelHeight(index: Int) = allPixels[index].size
}
