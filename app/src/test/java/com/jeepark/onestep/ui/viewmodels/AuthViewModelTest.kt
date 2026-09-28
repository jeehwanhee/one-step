package com.jeepark.onestep.ui.viewmodels

import android.content.Intent
import com.jeepark.onestep.MainDispatcherRule
import com.jeepark.onestep.collectEvents
import com.jeepark.onestep.data.model.User
import com.jeepark.onestep.data.repository.FakeAuthRepository
import com.jeepark.onestep.data.repository.FakeUserRepository
import com.jeepark.onestep.data.repository.SignInResult
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class AuthViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private class Fixture(user: User? = User(uid = "uid-1")) {
        val auth = FakeAuthRepository(uid = "uid-1")
        val users = FakeUserRepository(user = user)
        val viewModel = AuthViewModel(auth, users)
    }

    @Test
    fun `로그인에 성공하고 서버에 사용자 문서가 있으면 기존 사용자로 처리한다`() = runTest {
        val f = Fixture(user = User(uid = "uid-1", nickname = "테스터"))
        val results = collectEvents(f.viewModel.results)

        f.viewModel.onSignInResult(null)

        assertEquals(listOf<LoginResult>(LoginResult.ExistingUser), results)
    }

    @Test
    fun `로그인에 성공했지만 서버에 사용자 문서가 없으면 신규 사용자로 처리한다`() = runTest {
        val f = Fixture(user = null)
        val results = collectEvents(f.viewModel.results)

        f.viewModel.onSignInResult(null)

        assertEquals(listOf<LoginResult>(LoginResult.NewUser), results)
    }

    @Test
    fun `서버 조회가 실패하면 신규 사용자로 오판하지 않고 오류로 처리한다`() = runTest {
        val f = Fixture()
        f.users.shouldFail = true
        val results = collectEvents(f.viewModel.results)

        f.viewModel.onSignInResult(null)

        assertEquals(listOf<LoginResult>(LoginResult.Error), results)
    }

    @Test
    fun `로그인 자체가 실패하면 오류로 처리한다`() = runTest {
        val f = Fixture()
        f.auth.signInResult = SignInResult.Failed
        val results = collectEvents(f.viewModel.results)

        f.viewModel.onSignInResult(null)

        assertEquals(listOf<LoginResult>(LoginResult.Error), results)
    }

    @Test
    fun `사용자가 로그인 화면을 직접 닫으면 아무 결과도 전달하지 않는다`() = runTest {
        val f = Fixture()
        f.auth.signInResult = SignInResult.Cancelled
        val results = collectEvents(f.viewModel.results)

        f.viewModel.onSignInResult(null)

        assertTrue(results.isEmpty())
    }

    @Test
    fun `로그인에 성공했는데 uid를 읽을 수 없으면 오류로 처리한다`() = runTest {
        val f = Fixture()
        f.auth.uid = null
        val results = collectEvents(f.viewModel.results)

        f.viewModel.onSignInResult(null)

        assertEquals(listOf<LoginResult>(LoginResult.Error), results)
    }

    @Test
    fun `로그인 결과로 받은 인텐트를 인증 저장소에 그대로 넘긴다`() = runTest {
        val f = Fixture()
        val data = Intent()

        f.viewModel.onSignInResult(data)

        assertEquals(listOf<Intent?>(data), f.auth.signInIntents)
    }

    @Test
    fun `결과는 한 번만 전달된다`() = runTest {
        val f = Fixture()
        f.viewModel.onSignInResult(null)

        val first = collectEvents(f.viewModel.results)
        val second = collectEvents(f.viewModel.results)

        assertEquals(1, first.size)
        assertTrue(second.isEmpty())
    }
}
