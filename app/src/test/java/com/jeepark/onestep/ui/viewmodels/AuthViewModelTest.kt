package com.jeepark.onestep.ui.viewmodels

import android.content.Intent
import com.jeepark.onestep.MainDispatcherRule
import com.jeepark.onestep.data.model.User
import com.jeepark.onestep.data.repository.AccountService
import com.jeepark.onestep.data.repository.DeleteResult
import com.jeepark.onestep.data.repository.FakeAuthRepository
import com.jeepark.onestep.data.repository.FakeSettingsRepository
import com.jeepark.onestep.data.repository.FakeUserRepository
import com.jeepark.onestep.data.repository.SignInResult
import com.jeepark.onestep.util.FakeNotificationScheduler
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class AuthViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private class Fixture(user: User? = User(uid = "uid-1")) {
        val auth = FakeAuthRepository(uid = "uid-1")
        val users = FakeUserRepository(user = user)
        val settings = FakeSettingsRepository(lastAccessMillis = 1_000L)
        val scheduler = FakeNotificationScheduler().apply { schedule() }
        val viewModel = AuthViewModel(auth, users, AccountService(auth, users, settings, scheduler))

        /** 로그인 결과 콜백이 불린 순서. */
        val events = mutableListOf<String>()

        fun login(data: Intent? = null) = viewModel.login(
            data = data,
            onNewUser = { events.add("new") },
            onExistingUser = { events.add("existing") },
            onError = { events.add("error") },
        )
    }

    // ===== 로그인 =====

    @Test
    fun `로그인에 성공하고 서버에 사용자 문서가 있으면 기존 사용자로 처리한다`() {
        val f = Fixture(user = User(uid = "uid-1", nickname = "테스터"))

        f.login()

        assertEquals(listOf("existing"), f.events)
    }

    @Test
    fun `로그인에 성공했지만 서버에 사용자 문서가 없으면 신규 사용자로 처리한다`() {
        val f = Fixture(user = null)

        f.login()

        assertEquals(listOf("new"), f.events)
    }

    @Test
    fun `서버 조회가 실패하면 신규 사용자로 오판하지 않고 오류로 처리한다`() {
        val f = Fixture()
        f.users.shouldFail = true

        f.login()

        assertEquals(listOf("error"), f.events)
    }

    @Test
    fun `로그인 자체가 실패하면 오류로 처리한다`() {
        val f = Fixture()
        f.auth.signInResult = SignInResult.Failed

        f.login()

        assertEquals(listOf("error"), f.events)
    }

    @Test
    fun `사용자가 로그인 화면을 직접 닫으면 아무 콜백도 부르지 않는다`() {
        val f = Fixture()
        f.auth.signInResult = SignInResult.Cancelled

        f.login()

        assertTrue(f.events.isEmpty())
    }

    @Test
    fun `로그인에 성공했는데 uid를 읽을 수 없으면 오류로 처리한다`() {
        val f = Fixture()
        f.auth.uid = null

        f.login()

        assertEquals(listOf("error"), f.events)
    }

    @Test
    fun `로그인 결과로 받은 인텐트를 인증 저장소에 그대로 넘긴다`() {
        val f = Fixture()
        val data = Intent()

        f.login(data)

        assertEquals(listOf<Intent?>(data), f.auth.signInIntents)
    }

    // ===== 로그아웃·삭제 =====

    @Test
    fun `로그아웃하면 인증과 알림 예약, 접속 기록이 정리된다`() {
        val f = Fixture()

        f.viewModel.signOut()

        assertNull(f.viewModel.currentUid)
        assertEquals(0L, f.settings.lastAccessMillis)
        assertEquals(1, f.scheduler.cancelCount)
    }

    @Test
    fun `계정 삭제 결과가 그대로 전달된다`() {
        val success = Fixture()
        val authFailed = Fixture().apply { auth.deleteAuthResult = Result.failure(IllegalStateException("최근 로그인 필요")) }
        val dataFailed = Fixture().apply { users.shouldFail = true }
        val results = mutableListOf<DeleteResult>()

        success.viewModel.deleteAccount { results.add(it) }
        authFailed.viewModel.deleteAccount { results.add(it) }
        dataFailed.viewModel.deleteAccount { results.add(it) }

        assertEquals(
            listOf(DeleteResult.Success, DeleteResult.DataDeletedButAuthFailed, DeleteResult.Failed),
            results,
        )
    }

    @Test
    fun `현재 사용자 정보는 인증 저장소에서 읽는다`() {
        val f = Fixture()

        assertEquals("uid-1", f.viewModel.currentUid)
        assertEquals("tester@example.com", f.viewModel.currentEmail)
    }
}
