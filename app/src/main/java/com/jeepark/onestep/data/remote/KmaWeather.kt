package com.jeepark.onestep.data.remote

import com.jeepark.onestep.domain.model.Coordinates
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

// 기상청 초단기실황 조회에 필요한 계산과 응답 해석 (순수 함수, Android·네트워크 의존 없음).

/** 날씨를 알 수 없을 때 Gemini 프롬프트에 넣는 문구. */
const val WEATHER_UNKNOWN = "정보 없음"

/** 기상청 발표 시각은 한국 표준시 기준이다. 기기 시간대와 무관하게 이 시간대로 계산한다. */
val KMA_ZONE: ZoneId = ZoneId.of("Asia/Seoul")

/** 초단기실황 조회 기준 일시. [date]는 yyyyMMdd, [time]은 HH00. */
data class KmaBaseTime(val date: String, val time: String)

/**
 * 초단기실황은 매시 정시에 발표되고 약 40분 뒤에 조회할 수 있다.
 * 발표 지연을 피하려고 안전하게 **한 시간 전 정시**를 기준으로 삼는다.
 */
fun kmaBaseTime(now: ZonedDateTime): KmaBaseTime {
    val base = now.withZoneSameInstant(KMA_ZONE).minusHours(1)
    return KmaBaseTime(
        date = base.format(DateTimeFormatter.ofPattern("yyyyMMdd", Locale.ROOT)),
        time = "%02d00".format(Locale.ROOT, base.hour),
    )
}

/**
 * 위경도 → 기상청 격자 좌표(nx, ny) 변환 (LCC DFS, 기상청 표준 공식).
 * 전국 어디든 변환할 수 있다.
 */
fun toKmaGrid(coordinates: Coordinates): Pair<Int, Int> {
    val re = 6371.00877 / 5.0                // 지구 반경 / 격자 간격(km)
    val degrad = Math.PI / 180.0
    val slat1 = 30.0 * degrad                // 투영 위도 1
    val slat2 = 60.0 * degrad                // 투영 위도 2
    val olon = 126.0 * degrad                // 기준점 경도
    val olat = 38.0 * degrad                 // 기준점 위도
    val xo = 43.0                            // 기준점 X 격자
    val yo = 136.0                           // 기준점 Y 격자

    var sn = Math.tan(Math.PI * 0.25 + slat2 * 0.5) / Math.tan(Math.PI * 0.25 + slat1 * 0.5)
    sn = Math.log(Math.cos(slat1) / Math.cos(slat2)) / Math.log(sn)
    var sf = Math.tan(Math.PI * 0.25 + slat1 * 0.5)
    sf = Math.pow(sf, sn) * Math.cos(slat1) / sn
    var ro = Math.tan(Math.PI * 0.25 + olat * 0.5)
    ro = re * sf / Math.pow(ro, sn)

    var ra = Math.tan(Math.PI * 0.25 + coordinates.lat * degrad * 0.5)
    ra = re * sf / Math.pow(ra, sn)
    var theta = coordinates.lng * degrad - olon
    if (theta > Math.PI) theta -= 2.0 * Math.PI
    if (theta < -Math.PI) theta += 2.0 * Math.PI
    theta *= sn

    val nx = (ra * Math.sin(theta) + xo + 0.5).toInt()
    val ny = (ro - ra * Math.cos(theta) + yo + 0.5).toInt()
    return Pair(nx, ny)
}

/** 초단기실황 응답 항목으로 "기온 3.0°C, 비" 같은 한 줄 설명을 만든다. 기온이 없으면 [WEATHER_UNKNOWN]. */
fun describeWeather(items: List<KmaItem>): String {
    val temp = items.firstOrNull { it.category == "T1H" }?.obsrValue
    val pty = items.firstOrNull { it.category == "PTY" }?.obsrValue
    val precipitation = when (pty) {
        "0" -> "강수 없음"
        "1" -> "비"
        "2" -> "비/눈"
        "3" -> "눈"
        "5" -> "빗방울"
        "6" -> "빗방울/눈날림"
        "7" -> "눈날림"
        else -> "강수 정보 없음"
    }
    return if (temp != null) "기온 ${temp}°C, $precipitation" else WEATHER_UNKNOWN
}
