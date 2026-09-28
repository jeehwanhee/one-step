package com.jeepark.onestep.platform.location

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.jeepark.onestep.domain.model.Coordinates

/**
 * 사용자의 현재 위치(퀘스트 장소 매칭·날씨 조회용). 전역 변수 대신 이 인터페이스로 주고받는다.
 * 테스트에서는 FakeLocationProvider로 교체할 수 있다.
 */
interface LocationProvider {
    /** 마지막으로 확인한 위치. 아직 없으면(권한 없음·위치 꺼짐·아직 조회 전 포함) null. */
    val current: Coordinates?

    /** 위치를 새로 조회해서 [current]를 갱신한다. 위치 권한이 없으면 아무것도 하지 않는다. */
    fun refresh()
}

/** Google Play 서비스의 Fused Location 기반 구현. */
class FusedLocationProvider(context: Context) : LocationProvider {

    private val appContext = context.applicationContext

    // 위도·경도를 한 값으로 들고 있어서 읽는 쪽이 항상 같은 조회의 쌍을 본다
    @Volatile
    override var current: Coordinates? = null
        private set

    @SuppressLint("MissingPermission")
    override fun refresh() {
        // 권한 체크 — 거부 상태에서 호출하면 SecurityException이 나므로 미리 막는다
        val granted = ContextCompat.checkSelfPermission(
            appContext, android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == android.content.pm.PackageManager.PERMISSION_GRANTED
        if (!granted) return

        val client = LocationServices.getFusedLocationProviderClient(appContext)
        val cancellation = CancellationTokenSource()

        client.getCurrentLocation(Priority.PRIORITY_BALANCED_POWER_ACCURACY, cancellation.token)
            .addOnSuccessListener { location ->
                if (location != null) current = Coordinates(location.latitude, location.longitude)
            }
    }
}
