package com.jeepark.onestep.ui.screens

import androidx.compose.ui.graphics.Color
import com.jeepark.onestep.data.model.Animal
import com.jeepark.onestep.data.model.AnimalIds
import com.jeepark.onestep.data.model.AnimalProfile
import com.jeepark.onestep.data.model.AnimalRegistry
import com.jeepark.onestep.data.model.MAX_TIER
import com.jeepark.onestep.util.PixelSprite
import org.junit.Assert.assertEquals
import org.junit.Test

class ParkLayoutsTest {

    // ===== 배치 데이터 =====

    @Test
    fun `모든 배치는 등록된 모든 동물의 위치를 가지고 모르는 동물은 없다`() {
        val registered = AnimalRegistry.all.map { it.id }.toSet()

        PARK_VARIANTS.forEachIndexed { index, placements ->
            assertEquals("variant $index", registered, placements.keys)
        }
    }

    @Test
    fun `배치 좌표는 기존 화면과 같다`() {
        // 옛 ParkBackground.variants 표에서 옮긴 값 중 대표 지점(배치마다 첫·마지막 동물과 하늘 동물)
        assertEquals(AnimalPlacement(0.68f, 0.42f), PARK_VARIANTS[0].getValue(AnimalIds.CHICK))
        assertEquals(AnimalPlacement(0.25f, 0.40f), PARK_VARIANTS[0].getValue(AnimalIds.BLUEBIRD))
        assertEquals(AnimalPlacement(0.12f, 0.45f), PARK_VARIANTS[0].getValue(AnimalIds.DOLPHIN))
        assertEquals(AnimalPlacement(0.48f, 0.58f), PARK_VARIANTS[1].getValue(AnimalIds.CHICK))
        assertEquals(AnimalPlacement(0.55f, 0.70f), PARK_VARIANTS[1].getValue(AnimalIds.BLUEBIRD))
        assertEquals(AnimalPlacement(0.63f, 0.10f), PARK_VARIANTS[2].getValue(AnimalIds.CHICK))
        assertEquals(AnimalPlacement(0.75f, 0.90f), PARK_VARIANTS[2].getValue(AnimalIds.BLUEBIRD))
        assertEquals(AnimalPlacement(0.12f, 0.25f), PARK_VARIANTS[2].getValue(AnimalIds.DOLPHIN))
        assertEquals(AnimalPlacement(0.06f, 0.0f), PARK_VARIANTS[3].getValue(AnimalIds.CHICK))
        assertEquals(AnimalPlacement(0.40f, 0.80f), PARK_VARIANTS[3].getValue(AnimalIds.BLUEBIRD))
        assertEquals(AnimalPlacement(0.12f, 0.25f), PARK_VARIANTS[3].getValue(AnimalIds.DOLPHIN))
    }

    // ===== 티어별로 나타나는 동물 =====

    @Test
    fun `티어가 오를수록 동물이 레지스트리 순서대로 늘어난다`() {
        for (variant in PARK_VARIANTS.indices) {
            assertEquals(
                listOf(AnimalIds.CHICK, AnimalIds.TURTLE, AnimalIds.CAT, AnimalIds.DOG),
                placedAnimals(variant, 3).map { it.animal.id },
            )
            assertEquals(AnimalRegistry.all.map { it.id }, placedAnimals(variant, MAX_TIER).map { it.animal.id })
        }
    }

    // ===== 세로 위치 계산 =====

    private fun testAnimal(rows: Int, isSky: Boolean) = Animal(
        id = "test", name = "테스트", unlockTier = 0, isSky = isSky,
        sprite = PixelSprite(Array(rows) { IntArray(4) { 1 } }, listOf(Color.Transparent, Color.Black)),
        messages = listOf("안녕"), profile = AnimalProfile("성격", "좋아하는 것", "사는 곳"),
    )

    private val areaHeight = 600f
    private val pixelSize = 2f // 10행 스프라이트 → 높이 20px

    @Test
    fun `지면 동물은 영역의 1_3 지점에서 88퍼센트 지점 사이에서 비율만큼 내려온다`() {
        val animal = testAnimal(rows = 10, isSky = false)
        // 지면 영역: 200 ~ 528, 동물 높이 20 → 움직일 수 있는 범위 308
        assertEquals(200f, animalTopY(PlacedAnimal(animal, AnimalPlacement(0.5f, 0f)), areaHeight, pixelSize), 0.001f)
        assertEquals(354f, animalTopY(PlacedAnimal(animal, AnimalPlacement(0.5f, 0.5f)), areaHeight, pixelSize), 0.001f)
        assertEquals(508f, animalTopY(PlacedAnimal(animal, AnimalPlacement(0.5f, 1f)), areaHeight, pixelSize), 0.001f)
    }

    @Test
    fun `하늘 동물은 위쪽 1_3 영역 안에서 비율만큼 내려온다`() {
        val animal = testAnimal(rows = 10, isSky = true)
        // 하늘 영역: 0 ~ 200, 동물 높이 20 → 움직일 수 있는 범위 180
        assertEquals(0f, animalTopY(PlacedAnimal(animal, AnimalPlacement(0.5f, 0f)), areaHeight, pixelSize), 0.001f)
        assertEquals(90f, animalTopY(PlacedAnimal(animal, AnimalPlacement(0.5f, 0.5f)), areaHeight, pixelSize), 0.001f)
        assertEquals(180f, animalTopY(PlacedAnimal(animal, AnimalPlacement(0.5f, 1f)), areaHeight, pixelSize), 0.001f)
    }
}
