package com.jeepark.onestep.util

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlin.math.pow
import kotlin.math.sqrt

object LocationHelper {

    // 위치 권한 없을 때 기본 지역
    var currentAreaName: String = "강남역"

    // 사용자 GPS 위경도 (퀘스트 장소 매칭용)
    @Volatile var currentLat: Double? = null
    @Volatile var currentLng: Double? = null

    // 서울시 실시간 도시데이터 API 주요 지역 목록 (지역명, 위도, 경도)
    private val SEOUL_AREAS = listOf(
        Triple("강남역",          37.4979, 127.0276),
        Triple("건대입구역",       37.5406, 127.0694),
        Triple("홍대입구역",       37.5574, 126.9242),
        Triple("명동",            37.5636, 126.9840),
        Triple("이태원",           37.5347, 126.9937),
        Triple("여의도",           37.5215, 126.9246),
        Triple("잠실",            37.5135, 127.1013),
        Triple("신촌·이대",        37.5568, 126.9364),
        Triple("경복궁",           37.5796, 126.9770),
        Triple("광화문·덕수궁",    37.5713, 126.9744),
        Triple("인사동·익선동",    37.5741, 126.9861),
        Triple("동대문",           37.5710, 127.0097),
        Triple("성수",            37.5442, 127.0556),
        Triple("왕십리",           37.5613, 127.0385),
        Triple("서울숲",           37.5446, 127.0383),
        Triple("용산",            37.5300, 126.9647),
        Triple("서울역·남대문",    37.5547, 126.9706),
        Triple("청계천·을지로",    37.5698, 126.9869),
        Triple("창덕궁·종묘",      37.5788, 126.9931),
        Triple("북촌한옥마을",     37.5820, 126.9844),
        Triple("상암DMC",          37.5757, 126.8897),
        Triple("노량진",           37.5133, 126.9390),
        Triple("가산디지털단지",   37.4813, 126.8823),
        Triple("노원",            37.6559, 127.0741),
        Triple("수유·강북",        37.6392, 127.0256),
        Triple("불광·은평",        37.6024, 126.9298),
        Triple("천호·강동",        37.5385, 127.1237),
        Triple("뚝섬한강공원",     37.5301, 127.0671),
        Triple("반포한강공원",     37.5056, 126.9943),
        Triple("고속터미널",       37.5046, 127.0047),
        Triple("강남 COEX",        37.5115, 127.0595),
        Triple("수색·증산",        37.5775, 126.8954),
        Triple("연남·연트럴파크",  37.5640, 126.9257),
        Triple("독립문",           37.5727, 126.9594),
    )

    fun findNearestArea(lat: Double, lng: Double): String {
        return SEOUL_AREAS.minByOrNull { (_, aLat, aLng) ->
            sqrt((lat - aLat).pow(2) + (lng - aLng).pow(2))
        }?.first ?: "강남역"
    }

    @SuppressLint("MissingPermission")
    fun updateLocation(context: Context, onResult: (String) -> Unit) {
        // 권한 체크 — 거부 상태에서 호출 시 SecurityException 방지
        val granted = androidx.core.content.ContextCompat.checkSelfPermission(
            context, android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == android.content.pm.PackageManager.PERMISSION_GRANTED
        if (!granted) {
            onResult(currentAreaName)
            return
        }

        val client = LocationServices.getFusedLocationProviderClient(context)
        val cts    = CancellationTokenSource()

        client.getCurrentLocation(Priority.PRIORITY_BALANCED_POWER_ACCURACY, cts.token)
            .addOnSuccessListener { location ->
                if (location != null) {
                    currentLat = location.latitude
                    currentLng = location.longitude
                    currentAreaName = findNearestArea(location.latitude, location.longitude)
                }
                onResult(currentAreaName)
            }
            .addOnFailureListener {
                onResult(currentAreaName)
            }
    }
}
