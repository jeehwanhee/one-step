package com.jeepark.onestep.data.model


// 1. 전체 응답 구조
data class SeoulCityDataResponse(
    val CITYDATA: CityDataContents?
)

// 2. 내부 데이터 콘텐츠
data class CityDataContents(
    val WEATHER_STTS: List<WeatherStatus>?,    // 날씨 정보 리스트
    val LIVE_PPLTN_STTS: List<PopulationStatus>? // 인구 정보 (덤으로 활용!)
)

// 3. 날씨 상세 정보
data class WeatherStatus(
    val TEMP: String,            // 기온
    val SENSIBLE_TEMP: String,   // 체감온도
    val WEATHER_MSG: String,     // 날씨 메시지 (맑음, 흐림 등)
    val PCP_MSG: String,         // 강수 관련 메시지
    val PM10: String,            // 미세먼지
    val UV_INDEX: String         // 자외선 지수
)

// 4. 인구 혼잡도
data class PopulationStatus(
    val AREA_CONGEST_LVL: String, // 장소 혼잡도 (여유, 보통, 약간 혼잡, 혼잡)
    val AREA_CONGEST_MSG: String  // 혼잡도 상세 메시지
)