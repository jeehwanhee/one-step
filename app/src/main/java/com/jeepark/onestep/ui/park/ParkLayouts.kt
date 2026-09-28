package com.jeepark.onestep.ui.park

import com.jeepark.onestep.ui.animal.Animal
import com.jeepark.onestep.ui.animal.AnimalIds
import com.jeepark.onestep.ui.animal.AnimalRegistry

// 공원 화면(ParkBackground)에서 동물이 놓이는 위치와 그 계산.
// 화면을 그리지 않는 순수 데이터·계산이라 단위 테스트할 수 있다.

/**
 * 공원 안 동물 한 마리의 위치. 값은 모두 영역에 대한 비율(0..1)이다.
 * @param x 가로 위치(동물 왼쪽 끝 기준).
 * @param y 세로 위치. 하늘 동물은 하늘 영역 안에서, 나머지는 지면 영역 안에서의 비율이다.
 */
internal data class AnimalPlacement(val x: Float, val y: Float)

/** 공원에 실제로 그려지는 동물: 동물 정보 + 이번 배치에서의 위치. */
internal data class PlacedAnimal(val animal: Animal, val placement: AnimalPlacement)

/**
 * 공원 배치 4종. 앱을 켤 때마다 하나가 무작위로 골라진다.
 * 각 배치는 [AnimalRegistry]의 모든 동물 위치를 가져야 한다(빠지면 그 동물이 공원에 나오지 않는다).
 */
internal val PARK_VARIANTS: List<Map<String, AnimalPlacement>> = listOf(
    // variant 0
    mapOf(
        AnimalIds.CHICK    to AnimalPlacement(0.68f, 0.42f),
        AnimalIds.TURTLE   to AnimalPlacement(0.06f, 0.64f),
        AnimalIds.CAT      to AnimalPlacement(0.10f, 0.15f),
        AnimalIds.DOG      to AnimalPlacement(0.69f, 0.75f),
        AnimalIds.BLUEBIRD to AnimalPlacement(0.25f, 0.40f),
        AnimalIds.BEAR     to AnimalPlacement(0.40f, 0.90f),
        AnimalIds.HORSE    to AnimalPlacement(0.52f, 0.0f),
        AnimalIds.DOLPHIN  to AnimalPlacement(0.12f, 0.45f),
    ),
    // variant 1
    mapOf(
        AnimalIds.CHICK    to AnimalPlacement(0.48f, 0.58f),
        AnimalIds.TURTLE   to AnimalPlacement(0.66f, 0.83f),
        AnimalIds.CAT      to AnimalPlacement(0.12f, 0.70f),
        AnimalIds.DOG      to AnimalPlacement(0.69f, 0.15f),
        AnimalIds.BLUEBIRD to AnimalPlacement(0.55f, 0.70f),
        AnimalIds.BEAR     to AnimalPlacement(0.36f, 0.10f),
        AnimalIds.HORSE    to AnimalPlacement(0.12f, 0.0f),
        AnimalIds.DOLPHIN  to AnimalPlacement(0.12f, 0.45f),
    ),
    // variant 2
    mapOf(
        AnimalIds.CHICK    to AnimalPlacement(0.63f, 0.10f),
        AnimalIds.TURTLE   to AnimalPlacement(0.42f, 0.45f),
        AnimalIds.CAT      to AnimalPlacement(0.73f, 0.78f),
        AnimalIds.DOG      to AnimalPlacement(0.40f, 0.90f),
        AnimalIds.BLUEBIRD to AnimalPlacement(0.75f, 0.90f),
        AnimalIds.BEAR     to AnimalPlacement(0.05f, 0.00f),
        AnimalIds.HORSE    to AnimalPlacement(0.14f, 0.70f),
        AnimalIds.DOLPHIN  to AnimalPlacement(0.12f, 0.25f),
    ),
    // variant 3
    mapOf(
        AnimalIds.CHICK    to AnimalPlacement(0.06f, 0.0f),
        AnimalIds.TURTLE   to AnimalPlacement(0.06f, 0.64f),
        AnimalIds.CAT      to AnimalPlacement(0.70f, 0.15f),
        AnimalIds.DOG      to AnimalPlacement(0.40f, 0.40f),
        AnimalIds.BLUEBIRD to AnimalPlacement(0.40f, 0.80f),
        AnimalIds.BEAR     to AnimalPlacement(0.64f, 0.64f),
        AnimalIds.HORSE    to AnimalPlacement(0.32f, 0.90f),
        AnimalIds.DOLPHIN  to AnimalPlacement(0.12f, 0.25f),
    ),
)

/**
 * [tier]에서 [variant] 배치의 공원에 나타나는 동물들.
 * 순서는 [AnimalRegistry.all] 순서이며 그리기와 탭 판정이 모두 이 순서를 따른다.
 */
internal fun placedAnimals(variant: Int, tier: Int): List<PlacedAnimal> {
    val placements = PARK_VARIANTS[variant]
    return AnimalRegistry.unlockedAt(tier).mapNotNull { animal ->
        placements[animal.id]?.let { PlacedAnimal(animal, it) }
    }
}

/**
 * 동물 스프라이트 윗변의 y 좌표(px).
 * 하늘 동물은 위쪽 1/3 영역, 나머지는 1/3 ~ 88% 영역 안에서 [AnimalPlacement.y] 비율만큼 내려온다.
 * @param areaHeight 공원 영역의 높이(px)
 * @param pixelSize 스프라이트 픽셀 한 칸의 크기(px)
 */
internal fun animalTopY(placed: PlacedAnimal, areaHeight: Float, pixelSize: Float): Float {
    val animalH = placed.animal.sprite.height * pixelSize
    val skyZoneBottom    = areaHeight / 3f
    val groundZoneTop    = areaHeight / 3f
    val groundZoneBottom = areaHeight * 0.88f
    return if (placed.animal.isSky) {
        val range = (skyZoneBottom - animalH).coerceAtLeast(0f)
        placed.placement.y * range
    } else {
        val range = (groundZoneBottom - animalH - groundZoneTop).coerceAtLeast(0f)
        groundZoneTop + placed.placement.y * range
    }
}
