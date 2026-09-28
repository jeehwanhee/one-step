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
