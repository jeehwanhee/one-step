package com.jeepark.onestep.data.repository

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SharedPrefsSettingsRepositoryTest {

    private val prefs = InMemorySharedPreferences()
    private val settings = SharedPrefsSettingsRepository(prefs)

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
