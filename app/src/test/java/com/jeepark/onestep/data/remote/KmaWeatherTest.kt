package com.jeepark.onestep.data.remote

import com.jeepark.onestep.domain.model.Coordinates
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.ZoneId
import java.time.ZonedDateTime

class KmaWeatherTest {

    // ===== 격자 좌표 변환 =====

    @Test
    fun `주요 도시의 위경도는 기상청이 공표한 격자 좌표로 변환된다`() {
        // 기대값은 기상청 공표 격자와, 같은 수식을 다른 언어로 따로 계산한 결과가 모두 일치하는 도시만 골랐다.
        // (격자는 5km 칸이라 행정구역의 대표 지점이 아닌 좌표는 칸 경계에서 값이 달라질 수 있어, 그런 도시는 뺐다)
        val expected = mapOf(
            "서울" to Triple(Coordinates(37.5665, 126.9780), 60, 127),
            "부산" to Triple(Coordinates(35.1796, 129.0756), 98, 76),
            "광주" to Triple(Coordinates(35.1595, 126.8526), 58, 74),
            "대전" to Triple(Coordinates(36.3504, 127.3845), 67, 100),
            "제주" to Triple(Coordinates(33.4996, 126.5312), 53, 38),
        )

        val actual = expected.mapValues { (_, spec) -> toKmaGrid(spec.first) }

        assertEquals(expected.mapValues { (_, spec) -> spec.second to spec.third }, actual)
    }

    // ===== 조회 기준 시각 =====

    @Test
    fun `기기 시간대와 상관없이 한국 시간 기준으로 계산한다`() {
        // 같은 순간(한국 2026-09-28 00:30)을 세 시간대로 표현해도 결과가 같다
        val instant = ZonedDateTime.of(2026, 9, 28, 0, 30, 0, 0, KMA_ZONE).toInstant()
        val expected = KmaBaseTime(date = "20260927", time = "2300")

        listOf("UTC", "America/New_York", "Asia/Seoul").forEach { zone ->
            assertEquals(zone, expected, kmaBaseTime(instant.atZone(ZoneId.of(zone))))
        }
    }
}
