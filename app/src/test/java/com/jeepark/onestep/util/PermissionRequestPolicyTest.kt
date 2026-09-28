package com.jeepark.onestep.util

import com.jeepark.onestep.data.repository.FakeSettingsRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PermissionRequestPolicyTest {

    private class Fixture(notificationsEnabled: Boolean = true, permissionsRequested: Boolean = false) {
        val settings = FakeSettingsRepository(
            notificationsEnabled = notificationsEnabled, permissionsRequested = permissionsRequested,
        )
        val scheduler = FakeNotificationScheduler()
        val policy = PermissionRequestPolicy(settings, scheduler)
    }

    // ===== 권한을 물어볼지 =====

    @Test
    fun `처음 실행하면 권한을 물어보고 물어봤다고 기록한다`() {
        val f = Fixture()

        assertTrue(f.policy.shouldRequestPermissions())
        assertTrue(f.settings.permissionsRequested)
    }

    // ===== 알림 예약 =====

    @Test
    fun `알림 권한이 있고 수신이 켜져 있으면 예약한다`() {
        val f = Fixture(notificationsEnabled = true)

        f.policy.scheduleIfAllowed(notificationPermissionGranted = true)

        assertEquals(1, f.scheduler.scheduleCount)
        assertTrue(f.scheduler.isScheduled)
    }

    @Test
    fun `수신을 꺼 두었으면 권한이 있어도 예약하지 않는다`() {
        val f = Fixture(notificationsEnabled = false)

        f.policy.scheduleIfAllowed(notificationPermissionGranted = true)

        assertEquals(0, f.scheduler.scheduleCount)
    }
}
