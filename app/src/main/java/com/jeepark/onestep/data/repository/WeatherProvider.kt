package com.jeepark.onestep.data.repository

import com.jeepark.onestep.data.model.KmaService
import com.jeepark.onestep.data.model.WEATHER_UNKNOWN
import com.jeepark.onestep.data.model.describeWeather
import com.jeepark.onestep.data.model.kmaBaseTime
import com.jeepark.onestep.data.model.toKmaGrid
import com.jeepark.onestep.util.LocationProvider
import kotlinx.coroutines.CancellationException
import java.time.Clock
import java.time.ZonedDateTime

/** 사용자 위치의 현재 날씨. 테스트에서는 FakeWeatherProvider로 교체할 수 있다. */
interface WeatherProvider {
    /** "기온 3.0°C, 비" 같은 한 줄 설명. 위치를 모르거나 조회에 실패하면 "정보 없음"이며 예외를 던지지 않는다. */
    suspend fun describeCurrentWeather(): String
}

/** 기상청 초단기실황 구현. */
class KmaWeatherProvider(
    private val location: LocationProvider,
    private val service: KmaService,
    private val apiKey: String,
    private val clock: Clock = Clock.systemDefaultZone(),
) : WeatherProvider {

    override suspend fun describeCurrentWeather(): String {
        val coordinates = location.current ?: return WEATHER_UNKNOWN
        return try {
            val (nx, ny) = toKmaGrid(coordinates)
            val base = kmaBaseTime(ZonedDateTime.now(clock))
            val response = service.getUltraSrtNcst(
                serviceKey = apiKey,
                baseDate = base.date, baseTime = base.time, nx = nx, ny = ny,
            )
            describeWeather(response.response?.body?.items?.item ?: emptyList())
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            android.util.Log.e("KmaWeather", "기상청 API 호출 실패", e)
            WEATHER_UNKNOWN
        }
    }
}
