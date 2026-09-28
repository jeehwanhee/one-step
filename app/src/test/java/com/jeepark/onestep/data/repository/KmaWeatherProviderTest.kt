package com.jeepark.onestep.data.repository

import com.jeepark.onestep.data.model.Coordinates
import com.jeepark.onestep.data.model.KmaBody
import com.jeepark.onestep.data.model.KmaItem
import com.jeepark.onestep.data.model.KmaItems
import com.jeepark.onestep.data.model.KmaResponse
import com.jeepark.onestep.data.model.KmaResponseBody
import com.jeepark.onestep.data.model.WEATHER_UNKNOWN
import com.jeepark.onestep.data.model.toKmaGrid
import com.jeepark.onestep.util.FakeLocationProvider
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.Clock
import java.time.Instant
import java.time.ZoneId

class KmaWeatherProviderTest {

    private val cityHall = Coordinates(37.5665, 126.9780)

    // 한국 시간 2026-09-28 10:30 → 조회 기준은 09:00
    private val clock: Clock = Clock.fixed(Instant.parse("2026-09-28T01:30:00Z"), ZoneId.of("UTC"))

    private fun responseOf(vararg items: KmaItem) =
        KmaResponse(KmaResponseBody(KmaBody(KmaItems(items.toList()))))

    private class Fixture(location: Coordinates?, clock: Clock) {
        val service = FakeKmaService()
        val provider = KmaWeatherProvider(FakeLocationProvider(location), service, apiKey = "TEST_KEY", clock = clock)
    }

    @Test
    fun `위치와 시각으로 격자 좌표와 기준 시각을 만들어 조회하고 결과를 한 줄로 설명한다`() = runTest {
        val f = Fixture(location = cityHall, clock = clock)
        f.service.response = responseOf(KmaItem("T1H", "3.0"), KmaItem("PTY", "1"))

        val text = f.provider.describeCurrentWeather()

        assertEquals("기온 3.0°C, 비", text)
        val (nx, ny) = toKmaGrid(cityHall)
        assertEquals(
            listOf(FakeKmaService.Call("TEST_KEY", baseDate = "20260928", baseTime = "0900", nx = nx, ny = ny)),
            f.service.calls,
        )
    }

    @Test
    fun `조회에 실패해도 예외를 던지지 않고 정보 없음을 돌려준다`() = runTest {
        val f = Fixture(location = cityHall, clock = clock)
        f.service.error = java.io.IOException("네트워크 오류")

        assertEquals(WEATHER_UNKNOWN, f.provider.describeCurrentWeather())
    }
}
