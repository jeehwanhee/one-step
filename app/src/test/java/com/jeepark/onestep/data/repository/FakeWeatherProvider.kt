package com.jeepark.onestep.data.repository

/** 테스트용 가짜 WeatherProvider. */
class FakeWeatherProvider(var text: String = "기온 20°C, 강수 없음") : WeatherProvider {

    var callCount = 0
        private set

    override suspend fun describeCurrentWeather(): String {
        callCount++
        return text
    }
}
