package com.jeepark.onestep.ui.viewmodels

import com.jeepark.onestep.MainDispatcherRule
import com.jeepark.onestep.collectEvents
import com.jeepark.onestep.data.model.IsolatedRecord
import com.jeepark.onestep.data.model.User
import com.jeepark.onestep.data.repository.FakeAuthRepository
import com.jeepark.onestep.data.repository.FakeUserRepository
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class InitViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val surveyed = listOf(IsolatedRecord(score = 50, recordedAt = 1L))

    private fun viewModel(
        user: User?,
        uid: String? = "uid-1",
        serverFails: Boolean = false,
        splashDelayMillis: Long = 0L,
    ) = InitViewModel(
        userRepository = FakeUserRepository(user = user, shouldFail = serverFails),
        authRepository = FakeAuthRepository(uid = uid),
        splashDelayMillis = splashDelayMillis,
    )

    // ===== 라우팅 5가지 =====

    @Test
    fun `로그인하지 않았으면 서버에 사용자가 있어도 로그인 화면으로 간다`() = runTest {
        val vm = viewModel(user = User(isolatedHistory = surveyed), uid = null)

        assertEquals(listOf(StartDestination.Auth), collectEvents(vm.destination))
    }

    @Test
    fun `서버에 사용자 문서가 없으면 로그인 화면으로 간다`() = runTest {
        val vm = viewModel(user = null)

        assertEquals(listOf(StartDestination.Auth), collectEvents(vm.destination))
    }

    @Test
    fun `서버 조회에 실패하면 로그인 화면으로 간다`() = runTest {
        val vm = viewModel(user = User(isolatedHistory = surveyed), serverFails = true)

        assertEquals(listOf(StartDestination.Auth), collectEvents(vm.destination))
    }

    @Test
    fun `설문을 한 번도 하지 않았으면 설문 화면으로 간다`() = runTest {
        val vm = viewModel(user = User(isolatedHistory = emptyList()))

        assertEquals(listOf(StartDestination.InitQuestion), collectEvents(vm.destination))
    }

    @Test
    fun `설문 후 퀘스트를 열 개 채웠으면 설문 화면으로 간다`() = runTest {
        val vm = viewModel(user = User(isolatedHistory = surveyed, questsSinceAssessment = 10))

        assertEquals(listOf(StartDestination.InitQuestion), collectEvents(vm.destination))
    }

    @Test
    fun `설문이 필요 없는 기존 사용자는 메인 화면으로 간다`() = runTest {
        val vm = viewModel(user = User(isolatedHistory = surveyed, questsSinceAssessment = 3))

        assertEquals(listOf(StartDestination.Main), collectEvents(vm.destination))
    }

    // ===== 스플래시 지연과 한 번만 전달 =====

    @Test
    fun `스플래시 시간이 지나기 전에는 이동하지 않는다`() = runTest {
        val vm = viewModel(user = User(isolatedHistory = surveyed), splashDelayMillis = SPLASH_DELAY_MILLIS)
        val destinations = collectEvents(vm.destination)

        advanceTimeBy(SPLASH_DELAY_MILLIS - 1)
        runCurrent()
        assertTrue(destinations.isEmpty())

        advanceTimeBy(1)
        runCurrent()
        assertEquals(listOf(StartDestination.Main), destinations)
    }

    @Test
    fun `스플래시 시간은 1500밀리초다`() {
        assertEquals(1500L, SPLASH_DELAY_MILLIS)
    }

    @Test
    fun `이동 신호는 한 번만 전달된다`() = runTest {
        val vm = viewModel(user = User(isolatedHistory = surveyed))

        val first = collectEvents(vm.destination)
        val second = collectEvents(vm.destination)

        assertEquals(1, first.size)
        assertTrue(second.isEmpty())
    }
}
