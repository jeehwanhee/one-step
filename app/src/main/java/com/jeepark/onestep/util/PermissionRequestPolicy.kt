package com.jeepark.onestep.util

import com.jeepark.onestep.data.repository.SettingsRepository

/**
 * 앱 시작 시 위치·알림 권한을 언제 물어보고 알림을 언제 예약할지의 정책.
 * 실제 권한 요청 창(런처)은 Activity에 남기고, "물어볼지·예약할지"의 판단과 기록만 여기서 한다.
 */
class PermissionRequestPolicy(
    private val settings: SettingsRepository,
    private val scheduler: NotificationScheduler,
) {

    /**
     * 지금 권한을 물어봐야 하는지. 앱을 처음 쓸 때 한 번만 true이고, true를 돌려주는 순간 "물어봤음"으로 기록한다
     * (회전·재구성 때 권한 창이 반복해서 뜨지 않게 한다).
     */
    fun shouldRequestPermissions(): Boolean {
        if (settings.permissionsRequested) return false
        settings.markPermissionsRequested()
        return true
    }

    /** 알림 권한이 있고 알림 수신이 켜져 있으면 안부 알림을 예약한다. */
    fun scheduleIfAllowed(notificationPermissionGranted: Boolean) {
        if (notificationPermissionGranted && settings.notificationsEnabled) scheduler.schedule()
    }
}
