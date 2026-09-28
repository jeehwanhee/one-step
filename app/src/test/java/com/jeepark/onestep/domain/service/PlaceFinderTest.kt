package com.jeepark.onestep.domain.service

import com.jeepark.onestep.domain.model.Coordinates
import com.jeepark.onestep.domain.model.Place
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import kotlin.random.Random

class PlaceFinderTest {

    private val cityHall = Coordinates(37.5665, 126.9780)

    /** 서울시청에서 북쪽으로 대략 [meters]만큼 떨어진 곳(위도 1도 ≈ 111.19km). */
    private fun northOf(base: Coordinates, meters: Double) =
        Coordinates(base.lat + meters / 111_195.0, base.lng)

    private fun place(name: String, meters: Double, type: String = "park"): Place {
        val at = northOf(cityHall, meters)
        return Place(type = type, name = name, lat = at.lat, lng = at.lng)
    }

    /** 여러 난수 시드로 돌려서 나온 장소 이름의 집합. */
    private fun outcomes(type: String, places: List<Place>, seeds: Int = 60): Set<String?> =
        (1..seeds).map { seed -> findNearestPlace(type, places, cityHall, Random(seed))?.name }.toSet()

    // ===== 위치를 모를 때, 장소가 없을 때 =====

    @Test
    fun `위치를 모르면 장소를 고르지 않는다`() {
        assertNull(findNearestPlace("park", listOf(place("공원", 500.0)), from = null, random = Random(1)))
    }

    // ===== 가까운 반경 안 (다양성을 위해 무작위) =====

    @Test
    fun `가까운 반경 안에 여럿이면 그중에서 무작위로 고르고 먼 곳은 고르지 않는다`() {
        val places = listOf(place("가까운 공원A", 1_000.0), place("가까운 공원B", 2_000.0), place("먼 공원", 20_000.0))

        val result = outcomes("park", places)

        assertEquals(setOf<String?>("가까운 공원A", "가까운 공원B"), result)
    }

    // ===== 넓은 반경, 그 밖 =====

    @Test
    fun `가까운 반경 밖이지만 넓은 반경 안이면 가장 가까운 곳을 고른다`() {
        // 공원: 가까운 반경 3km, 넓은 반경 12km
        val places = listOf(place("8km 공원", 8_000.0), place("10km 공원", 10_000.0))

        assertEquals(setOf<String?>("8km 공원"), outcomes("park", places))
    }
}
