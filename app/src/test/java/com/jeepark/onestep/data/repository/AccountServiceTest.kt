package com.jeepark.onestep.data.repository

import com.jeepark.onestep.data.model.User
import com.jeepark.onestep.util.FakeNotificationScheduler
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class AccountServiceTest {

    private class Fixture(uid: String? = "uid-1") {
        val auth = FakeAuthRepository(uid = uid)
        val users = FakeUserRepository(user = User(uid = "uid-1", nickname = "테스터"))
        val settings = FakeSettingsRepository(
            notificationsEnabled = true, lastAccessMillis = 1_000L, lastCheckInMark = 5, permissionsRequested = true,
        )
        val scheduler = FakeNotificationScheduler().apply { schedule() }
        val service = AccountService(auth, users, settings, scheduler)
    }

    // ===== 계정 삭제 =====

    @Test
    fun `계정을 삭제하면 데이터와 인증 계정이 지워지고 로그아웃되며 알림도 정리된다`() = runTest {
        val f = Fixture()

        val result = f.service.deleteAccount()

        assertEquals(DeleteResult.Success, result)
        assertNull(f.users.user)
        assertEquals(1, f.auth.deleteAuthCount)
        assertEquals(1, f.auth.signOutCount)
        assertFalse(f.scheduler.isScheduled)
        assertEquals(0L, f.settings.lastAccessMillis)
    }

    @Test
    fun `데이터 삭제에 실패하면 아무것도 바꾸지 않고 로그인 상태도 그대로다`() = runTest {
        val f = Fixture()
        f.users.shouldFail = true

        val result = f.service.deleteAccount()

        assertEquals(DeleteResult.Failed, result)
        assertNotNull(f.users.user)
        assertEquals(0, f.auth.deleteAuthCount)
        assertEquals(0, f.auth.signOutCount)
        assertEquals("uid-1", f.auth.currentUid)
        assertTrue(f.scheduler.isScheduled)
        assertEquals(1_000L, f.settings.lastAccessMillis)
    }

    @Test
    fun `인증 계정 삭제에 실패해도 데이터는 이미 지워졌으므로 로그아웃하고 알림을 정리한다`() = runTest {
        val f = Fixture()
        f.auth.deleteAuthResult = Result.failure(IllegalStateException("최근 로그인이 필요합니다"))

        val result = f.service.deleteAccount()

        assertEquals(DeleteResult.DataDeletedButAuthFailed, result)
        assertNull(f.users.user)
        assertEquals(1, f.auth.signOutCount)
        assertNull(f.auth.currentUid)
        assertFalse(f.scheduler.isScheduled)
        assertEquals(0L, f.settings.lastAccessMillis)
        assertEquals(0, f.settings.lastCheckInMark)
    }
}
