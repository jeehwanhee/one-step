package com.jeepark.onestep.data.repository

/**
 * 이 기기에 저장되는 앱 설정과 알림 상태. 설정 화면, 메인 화면, 알림 워커, 권한 요청이 같은 저장소를 쓴다.
 * 테스트에서는 FakeSettingsRepository로 교체할 수 있다.
 */
interface SettingsRepository {
    /** 안부 알림을 받을지. 기본값은 true. */
    val notificationsEnabled: Boolean

    /** 마지막으로 앱을 사용한 시각(epoch millis). 기록이 없으면 0. */
    val lastAccessMillis: Long

    /** 마지막으로 보낸 안부 알림 단계(경과 일수 기준). 아직 안 보냈으면 0. */
    val lastCheckInMark: Int

    /** 위치·알림 권한 요청을 이미 한 번 했는지. */
    val permissionsRequested: Boolean

    fun setNotificationsEnabled(enabled: Boolean)

    /** 앱을 사용했음을 기록한다. 안부 알림 단계도 처음부터 다시 시작한다. */
    fun recordAccess(atMillis: Long)

    /** [mark] 단계의 안부 알림을 보냈음을 기록한다. */
    fun recordCheckIn(mark: Int)

    fun markPermissionsRequested()

    /** 접속 기록과 안부 알림 단계를 지운다(로그아웃·탈퇴 시). 알림 수신 여부와 권한 요청 기록은 유지한다. */
    fun clearAccessRecord()
}
