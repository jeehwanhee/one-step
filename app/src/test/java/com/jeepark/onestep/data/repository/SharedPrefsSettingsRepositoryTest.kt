package com.jeepark.onestep.data.repository

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SharedPrefsSettingsRepositoryTest {

    private val prefs = InMemorySharedPreferences()
    private val settings = SharedPrefsSettingsRepository(prefs)

    // ===== 이미 설치된 기기의 저장값을 읽기 위해 바뀌면 안 되는 이름 =====

    @Test
    fun `파일 이름과 키는 기존 설치 기기에 저장된 값과 같다`() {
        assertEquals("app_settings", SharedPrefsSettingsRepository.FILE_NAME)
        assertEquals("notification_enabled", SharedPrefsSettingsRepository.KEY_NOTIFICATIONS_ENABLED)
        assertEquals("last_access_date", SharedPrefsSettingsRepository.KEY_LAST_ACCESS)
        assertEquals("last_checkin_mark", SharedPrefsSettingsRepository.KEY_LAST_CHECKIN_MARK)
        assertEquals("perm_requested", SharedPrefsSettingsRepository.KEY_PERMISSIONS_REQUESTED)
    }

    @Test
    fun `옛 앱이 저장해 둔 값을 그대로 읽는다`() {
        prefs.edit()
            .putBoolean("notification_enabled", false)
            .putLong("last_access_date", 1_700_000_000_000L)
            .putInt("last_checkin_mark", 5)
            .putBoolean("perm_requested", true)
            .apply()

        assertFalse(settings.notificationsEnabled)
        assertEquals(1_700_000_000_000L, settings.lastAccessMillis)
        assertEquals(5, settings.lastCheckInMark)
        assertTrue(settings.permissionsRequested)
    }

    // ===== 기본값 =====

    @Test
    fun `아무것도 저장되지 않은 새 기기의 기본값`() {
        assertTrue(settings.notificationsEnabled)
        assertEquals(0L, settings.lastAccessMillis)
        assertEquals(0, settings.lastCheckInMark)
        assertFalse(settings.permissionsRequested)
    }

    // ===== 쓰기 =====

    @Test
    fun `알림 수신 여부를 저장한다`() {
        settings.setNotificationsEnabled(false)
        assertFalse(settings.notificationsEnabled)

        settings.setNotificationsEnabled(true)
        assertTrue(settings.notificationsEnabled)
    }

    @Test
    fun `접속을 기록하면 시각이 저장되고 안부 알림 단계는 처음으로 돌아간다`() {
        settings.recordCheckIn(10)

        settings.recordAccess(atMillis = 5_000L)

        assertEquals(5_000L, settings.lastAccessMillis)
        assertEquals(0, settings.lastCheckInMark)
    }

    @Test
    fun `안부 알림 단계를 기록해도 접속 시각은 바뀌지 않는다`() {
        settings.recordAccess(5_000L)

        settings.recordCheckIn(2)

        assertEquals(2, settings.lastCheckInMark)
        assertEquals(5_000L, settings.lastAccessMillis)
    }

    @Test
    fun `권한 요청을 했다고 기록한다`() {
        settings.markPermissionsRequested()

        assertTrue(settings.permissionsRequested)
    }

    @Test
    fun `접속 기록을 지워도 알림 수신 여부와 권한 요청 기록은 남는다`() {
        settings.setNotificationsEnabled(false)
        settings.markPermissionsRequested()
        settings.recordAccess(5_000L)
        settings.recordCheckIn(5)

        settings.clearAccessRecord()

        assertEquals(0L, settings.lastAccessMillis)
        assertEquals(0, settings.lastCheckInMark)
        assertFalse(settings.notificationsEnabled)
        assertTrue(settings.permissionsRequested)
        assertEquals(setOf("notification_enabled", "perm_requested"), prefs.keys)
    }
}
