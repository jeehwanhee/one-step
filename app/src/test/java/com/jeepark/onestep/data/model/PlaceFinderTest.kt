package com.jeepark.onestep.data.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
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

    // ===== 거리 =====

    @Test
    fun `위도 1도 차이는 약 111킬로미터다`() {
        val meters = distanceMeters(Coordinates(0.0, 0.0), Coordinates(1.0, 0.0))

        assertEquals(111_195.0, meters, 100.0)
    }

    @Test
    fun `같은 지점의 거리는 0이고 방향과 상관없이 같다`() {
        val busan = Coordinates(35.1796, 129.0756)

        assertEquals(0.0, distanceMeters(cityHall, cityHall), 0.001)
        assertEquals(distanceMeters(cityHall, busan), distanceMeters(busan, cityHall), 0.001)
        assertEquals(325_000.0, distanceMeters(cityHall, busan), 5_000.0) // 서울-부산 직선거리 약 325km
    }

    // ===== 위치를 모를 때, 장소가 없을 때 =====

    @Test
    fun `위치를 모르면 장소를 고르지 않는다`() {
        assertNull(findNearestPlace("park", listOf(place("공원", 500.0)), from = null, random = Random(1)))
    }

    @Test
    fun `그 종류의 장소가 하나도 없으면 null이다`() {
        val places = listOf(place("도서관", 500.0, type = "library"))

        assertNull(findNearestPlace("park", places, cityHall, Random(1)))
        assertNull(findNearestPlace("park", emptyList(), cityHall, Random(1)))
    }

    @Test
    fun `다른 종류의 장소는 고르지 않는다`() {
        val places = listOf(place("도서관", 100.0, type = "library"), place("공원", 2_000.0))

        assertEquals(setOf<String?>("공원"), outcomes("park", places))
    }

    // ===== 가까운 반경 안 (다양성을 위해 무작위) =====

    @Test
    fun `가까운 반경 안에 여럿이면 그중에서 무작위로 고르고 먼 곳은 고르지 않는다`() {
        val places = listOf(place("가까운 공원A", 1_000.0), place("가까운 공원B", 2_000.0), place("먼 공원", 20_000.0))

        val result = outcomes("park", places)

        assertEquals(setOf<String?>("가까운 공원A", "가까운 공원B"), result)
    }

    @Test
    fun `가까운 반경 안에 하나뿐이면 그 장소만 고른다`() {
        val places = listOf(place("가까운 공원", 1_000.0), place("먼 공원", 8_000.0))

        assertEquals(setOf<String?>("가까운 공원"), outcomes("park", places))
    }

    @Test
    fun `같은 난수 시드면 같은 장소가 나온다`() {
        val places = listOf(place("공원A", 1_000.0), place("공원B", 2_000.0), place("공원C", 2_500.0))

        val first = findNearestPlace("park", places, cityHall, Random(42))
        val second = findNearestPlace("park", places, cityHall, Random(42))

        assertEquals(first, second)
    }

    // ===== 넓은 반경, 그 밖 =====

    @Test
    fun `가까운 반경 밖이지만 넓은 반경 안이면 가장 가까운 곳을 고른다`() {
        // 공원: 가까운 반경 3km, 넓은 반경 12km
        val places = listOf(place("8km 공원", 8_000.0), place("10km 공원", 10_000.0))

        assertEquals(setOf<String?>("8km 공원"), outcomes("park", places))
    }

    @Test
    fun `넓은 반경 밖이라도 그 종류의 장소가 있으면 가장 가까운 곳을 고른다`() {
        val places = listOf(place("30km 공원", 30_000.0), place("50km 공원", 50_000.0))

        assertEquals(setOf<String?>("30km 공원"), outcomes("park", places))
    }

    // ===== 종류별 반경 =====

    @Test
    fun `종류마다 가까운 반경이 다르다`() {
        val distances = listOf(5_000.0, 9_000.0)
        val parks = distances.map { place("공원 ${it.toInt()}m", it, type = "park") }
        val youthSpaces = distances.map { place("청년공간 ${it.toInt()}m", it, type = "youth_space") }

        // 공원은 3km까지만 가까운 곳이라 5km·9km는 모두 "넓은 반경" → 가장 가까운 5km만 나온다
        assertEquals(setOf<String?>("공원 5000m"), outcomes("park", parks))
        // 청년공간은 10km까지 가까운 곳이라 둘 다 후보 → 무작위
        assertEquals(setOf<String?>("청년공간 5000m", "청년공간 9000m"), outcomes("youth_space", youthSpaces))
    }

    @Test
    fun `알 수 없는 종류는 기본 반경(가까운 5km, 넓은 20km)을 쓴다`() {
        val near = listOf(place("카페A", 4_000.0, type = "cafe"), place("카페B", 4_500.0, type = "cafe"))
        val far = listOf(place("카페C", 6_000.0, type = "cafe"), place("카페D", 15_000.0, type = "cafe"))

        assertEquals(setOf<String?>("카페A", "카페B"), outcomes("cafe", near))
        assertEquals(setOf<String?>("카페C"), outcomes("cafe", far))
    }

    @Test
    fun `반경 경계 바로 안쪽과 바깥쪽을 구분한다`() {
        val inside = listOf(place("안쪽", 2_990.0), place("바깥쪽 가까운 곳", 3_010.0))

        assertTrue(outcomes("park", inside) == setOf<String?>("안쪽"))
    }
}
