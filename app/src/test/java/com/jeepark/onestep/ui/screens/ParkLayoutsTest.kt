package com.jeepark.onestep.ui.screens

import com.jeepark.onestep.data.model.AnimalRegistry
import org.junit.Assert.assertEquals
import org.junit.Test

class ParkLayoutsTest {

    // 새 동물을 등록하고 배치를 빠뜨리면 그 동물이 공원에 조용히 나오지 않는다
    @Test
    fun `모든 배치는 등록된 모든 동물의 위치를 가지고 모르는 동물은 없다`() {
        val registered = AnimalRegistry.all.map { it.id }.toSet()

        PARK_VARIANTS.forEachIndexed { index, placements ->
            assertEquals("variant $index", registered, placements.keys)
        }
    }
}
