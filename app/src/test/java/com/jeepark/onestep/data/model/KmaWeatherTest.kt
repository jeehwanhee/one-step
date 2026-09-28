package com.jeepark.onestep.data.model

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

    @Test
    fun `같은 도시 안에서도 5km 격자 경계를 넘으면 칸이 바뀐다`() {
        // 대구 시청 좌표(35.8714, 128.6014)는 칸 경계 바로 위(89,91)이고, 남쪽으로 0.005도 내려가면 (89,90)이다
        assertEquals(89 to 91, toKmaGrid(Coordinates(35.8714, 128.6014)))
        assertEquals(89 to 90, toKmaGrid(Coordinates(35.8664, 128.6014)))
    }

    @Test
    fun `북쪽으로 갈수록 y가 커지고 동쪽으로 갈수록 x가 커진다`() {
        val seoul = toKmaGrid(Coordinates(37.5665, 126.9780))
        val north = toKmaGrid(Coordinates(38.5665, 126.9780))
        val east = toKmaGrid(Coordinates(37.5665, 127.9780))

        assertEquals(true, north.second > seoul.second)
        assertEquals(true, east.first > seoul.first)
    }

    // ===== 조회 기준 시각 =====

    @Test
    fun `기준 시각은 한 시간 전 정시다`() {
        val now = ZonedDateTime.of(2026, 9, 28, 10, 30, 0, 0, KMA_ZONE)

        assertEquals(KmaBaseTime(date = "20260928", time = "0900"), kmaBaseTime(now))
    }

    @Test
    fun `시각이 한 자리여도 두 자리로 채운다`() {
        val now = ZonedDateTime.of(2026, 1, 5, 5, 10, 0, 0, KMA_ZONE)

        assertEquals(KmaBaseTime(date = "20260105", time = "0400"), kmaBaseTime(now))
    }

    @Test
    fun `자정 직후에는 전날 23시가 기준이다`() {
        val now = ZonedDateTime.of(2026, 9, 28, 0, 30, 0, 0, KMA_ZONE)

        assertEquals(KmaBaseTime(date = "20260927", time = "2300"), kmaBaseTime(now))
    }

    @Test
    fun `기기 시간대와 상관없이 한국 시간 기준으로 계산한다`() {
        // 같은 순간(한국 2026-09-28 00:30)을 세 시간대로 표현해도 결과가 같다
        val instant = ZonedDateTime.of(2026, 9, 28, 0, 30, 0, 0, KMA_ZONE).toInstant()
        val expected = KmaBaseTime(date = "20260927", time = "2300")

        listOf("UTC", "America/New_York", "Asia/Seoul").forEach { zone ->
            assertEquals(zone, expected, kmaBaseTime(instant.atZone(ZoneId.of(zone))))
        }
    }

    // ===== 응답 해석 =====

    private fun items(temp: String?, pty: String?) = listOfNotNull(
        temp?.let { KmaItem(category = "T1H", obsrValue = it) },
        pty?.let { KmaItem(category = "PTY", obsrValue = it) },
        KmaItem(category = "REH", obsrValue = "55"), // 상관없는 항목이 섞여 있어도 무시한다
    )

    @Test
    fun `기온과 강수 형태를 한 줄로 설명한다`() {
        assertEquals("기온 3.5°C, 강수 없음", describeWeather(items("3.5", "0")))
    }

    @Test
    fun `강수 형태 코드마다 설명이 붙는다`() {
        val expected = mapOf(
            "0" to "강수 없음", "1" to "비", "2" to "비/눈", "3" to "눈",
            "5" to "빗방울", "6" to "빗방울/눈날림", "7" to "눈날림",
        )

        expected.forEach { (code, text) ->
            assertEquals("pty=$code", "기온 10°C, $text", describeWeather(items("10", code)))
        }
    }

    @Test
    fun `알 수 없는 강수 코드나 없는 항목은 강수 정보 없음으로 쓴다`() {
        assertEquals("기온 10°C, 강수 정보 없음", describeWeather(items("10", "4")))
        assertEquals("기온 10°C, 강수 정보 없음", describeWeather(items("10", null)))
    }

    @Test
    fun `기온이 없으면 날씨를 알 수 없다`() {
        assertEquals(WEATHER_UNKNOWN, describeWeather(items(null, "0")))
        assertEquals(WEATHER_UNKNOWN, describeWeather(emptyList()))
    }

    @Test
    fun `날씨를 알 수 없을 때의 문구는 정보 없음이다`() {
        assertEquals("정보 없음", WEATHER_UNKNOWN)
    }
}
