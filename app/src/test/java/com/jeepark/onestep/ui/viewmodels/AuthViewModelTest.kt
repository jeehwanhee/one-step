package com.jeepark.onestep.ui.viewmodels

import com.jeepark.onestep.MainDispatcherRule
import com.jeepark.onestep.collectEvents
import com.jeepark.onestep.data.model.User
import com.jeepark.onestep.data.repository.FakeAuthRepository
import com.jeepark.onestep.data.repository.FakeUserRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
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
}
