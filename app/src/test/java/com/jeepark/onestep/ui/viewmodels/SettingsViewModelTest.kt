package com.jeepark.onestep.ui.viewmodels

import com.jeepark.onestep.MainDispatcherRule
import com.jeepark.onestep.collectEvents
import com.jeepark.onestep.data.model.User
import com.jeepark.onestep.data.repository.AccountService
import com.jeepark.onestep.data.repository.FakeAuthRepository
import com.jeepark.onestep.data.repository.FakeSettingsRepository
import com.jeepark.onestep.data.repository.FakeUserRepository
import com.jeepark.onestep.util.FakeNotificationScheduler
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class SettingsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private class Fixture(
        user: User? = User(uid = "uid-1", nickname = "테스터", notificationAgreed = true),
        uid: String? = "uid-1",
        notificationsEnabled: Boolean = true,
        gate: CompletableDeferred<Unit>? = null,
    ) {
        val auth = FakeAuthRepository(uid = uid, email = "tester@example.com")
        val users = FakeUserRepository(user = user).also { it.writeGate = gate }
        val settings = FakeSettingsRepository(notificationsEnabled = notificationsEnabled, lastAccessMillis = 1_000L)
        val scheduler = FakeNotificationScheduler().apply { schedule() }
        val viewModel = SettingsViewModel(
            authRepository = auth,
            userRepository = users,
            settingsRepository = settings,
            notificationScheduler = scheduler,
            accountService = AccountService(auth, users, settings, scheduler),
        )
        val state get() = viewModel.state.value
    }

    // ===== 알림 스위치: 기기 설정 + 서버 + 예약을 함께 맞춘다 =====

    @Test
    fun `알림을 끄면 기기 설정과 서버 동의 여부가 꺼지고 예약이 취소된다`() {
        val f = Fixture()

        f.viewModel.onNotificationsToggled(false)

        assertFalse(f.state.notificationsEnabled)
        assertFalse(f.settings.notificationsEnabled)
        assertFalse(f.users.user!!.notificationAgreed)
        assertFalse(f.scheduler.isScheduled)
    }

    @Test
    fun `서버 확인이 오지 않아도 스위치와 예약은 바로 바뀌고 확인이 오면 서버에도 반영된다`() {
        val gate = CompletableDeferred<Unit>()
        val f = Fixture(gate = gate)

        f.viewModel.onNotificationsToggled(false)

        assertFalse(f.state.notificationsEnabled)
        assertFalse(f.settings.notificationsEnabled)
        assertFalse(f.scheduler.isScheduled)
        assertTrue(f.users.user!!.notificationAgreed) // 아직 서버 미반영

        gate.complete(Unit)

        assertFalse(f.users.user!!.notificationAgreed)
    }

    // ===== 로그아웃 =====

    @Test
    fun `로그아웃하면 인증 알림 예약 접속 기록이 정리되고 시작 화면으로 돌아간다`() = runTest {
        val f = Fixture()
        val events = collectEvents(f.viewModel.events)
        f.viewModel.showLogoutDialog()

        f.viewModel.confirmLogout()

        assertEquals(listOf<SettingsEvent>(SettingsEvent.LeftAccount), events)
        assertFalse(f.state.showLogoutDialog)
        assertEquals(1, f.auth.signOutCount)
        assertFalse(f.scheduler.isScheduled)
        assertEquals(0L, f.settings.lastAccessMillis)
    }

    // ===== 계정 탈퇴 =====

    @Test
    fun `탈퇴에 성공하면 다이얼로그가 닫히고 시작 화면으로 돌아간다`() = runTest {
        val f = Fixture()
        val events = collectEvents(f.viewModel.events)
        f.viewModel.showDeleteDialog()

        f.viewModel.confirmDelete()

        assertEquals(listOf<SettingsEvent>(SettingsEvent.LeftAccount), events)
        assertFalse(f.state.showDeleteDialog)
        assertFalse(f.state.isDeleting)
        assertNull(f.users.user)
    }

    @Test
    fun `인증 계정 삭제만 실패해도 데이터는 지워졌으므로 시작 화면으로 돌아간다`() = runTest {
        val f = Fixture()
        f.auth.deleteAuthResult = Result.failure(IllegalStateException("최근 로그인이 필요합니다"))
        val events = collectEvents(f.viewModel.events)
        f.viewModel.showDeleteDialog()

        f.viewModel.confirmDelete()

        assertEquals(listOf<SettingsEvent>(SettingsEvent.LeftAccount), events)
        assertNull(f.users.user)
        assertEquals(1, f.auth.signOutCount)
        assertFalse(f.scheduler.isScheduled)
    }

    @Test
    fun `데이터 삭제 자체가 실패하면 아무것도 바뀌지 않고 설정 화면에 남는다`() = runTest {
        val f = Fixture()
        f.users.shouldFail = true
        val events = collectEvents(f.viewModel.events)
        f.viewModel.showDeleteDialog()

        f.viewModel.confirmDelete()

        assertTrue(events.isEmpty())
        assertFalse(f.state.showDeleteDialog)
        assertFalse(f.state.isDeleting)
        assertEquals(0, f.auth.signOutCount)
        assertEquals("uid-1", f.auth.currentUid)
        assertTrue(f.scheduler.isScheduled)
    }
}
