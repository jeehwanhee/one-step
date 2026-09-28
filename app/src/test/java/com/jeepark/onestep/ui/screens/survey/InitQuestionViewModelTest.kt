package com.jeepark.onestep.ui.screens.survey

import com.jeepark.onestep.MainDispatcherRule
import com.jeepark.onestep.collectEvents
import com.jeepark.onestep.data.repository.FakeUserRepository
import com.jeepark.onestep.domain.model.InitQuestions
import com.jeepark.onestep.domain.model.User
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class InitQuestionViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private class Fixture(user: User? = User(uid = "uid-1", age = 25), gate: CompletableDeferred<Unit>? = null) {
        val users = FakeUserRepository(user = user).also { it.writeGate = gate }
        val viewModel = InitQuestionViewModel(users)
        val state get() = viewModel.state.value

        /** 모든 문항에 차례로 답하고 마지막 문항까지 이동한다. */
        fun answerAll(answers: List<String> = listOf("3", "7", "4", "2", "12", "1")) {
            answers.forEachIndexed { page, text ->
                viewModel.onAnswerChange(page, text)
                viewModel.goNext()
            }
        }
    }

    // ===== 제출 =====

    @Test
    fun `제출하면 답변이 문항 순서대로 저장되고 완료 이벤트가 나온다`() = runTest {
        val f = Fixture()
        val events = collectEvents(f.viewModel.events)
        f.answerAll(listOf("3", "7", "4", "2", "12", "1"))

        f.viewModel.submit()

        assertEquals(listOf<InitQuestionEvent>(InitQuestionEvent.Completed), events)
        assertEquals(
            InitQuestions(meal = 3, sleepTime = 7, shower = 4, outside = 2, hiki = 12, activeTime = 1),
            f.users.user!!.initQuestions,
        )
    }

    @Test
    fun `제출에 실패하면 실패 이벤트가 나오고 제출 중 상태가 풀린다`() = runTest {
        val f = Fixture()
        f.users.shouldFail = true
        val events = collectEvents(f.viewModel.events)
        f.answerAll()

        f.viewModel.submit()

        assertEquals(listOf<InitQuestionEvent>(InitQuestionEvent.Failed), events)
        assertFalse(f.state.isSubmitting)
        assertTrue(f.state.canSubmit)
    }

    @Test
    fun `제출 중에 다시 눌러도 한 번만 제출된다`() = runTest {
        val gate = CompletableDeferred<Unit>()
        val f = Fixture(gate = gate)
        val events = collectEvents(f.viewModel.events)
        f.answerAll()

        f.viewModel.submit()
        f.viewModel.submit()
        assertTrue(events.isEmpty()) // 서버 응답 전
        assertTrue(f.state.isSubmitting)

        gate.complete(Unit)

        assertEquals(1, events.size)
        assertEquals(1, f.users.user!!.isolatedHistory.size)
    }
}
