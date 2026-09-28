package com.jeepark.onestep.ui.viewmodels

import com.jeepark.onestep.MainDispatcherRule
import com.jeepark.onestep.collectEvents
import com.jeepark.onestep.data.model.INIT_QUESTIONS
import com.jeepark.onestep.data.model.InitQuestions
import com.jeepark.onestep.data.model.User
import com.jeepark.onestep.data.repository.FakeUserRepository
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
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

    // ===== 초기 상태와 답변 입력 =====

    @Test
    fun `처음에는 첫 문항이고 아무 문항에도 답하지 않았다`() {
        val f = Fixture()

        assertEquals(0, f.state.currentPage)
        assertEquals(INIT_QUESTIONS.size, f.state.pageCount)
        assertTrue(f.state.answers.all { it == null })
        assertFalse(f.state.canGoNext)
        assertFalse(f.state.canGoBack)
        assertFalse(f.state.canSubmit)
    }

    @Test
    fun `범위 안의 답변은 그대로 저장된다`() {
        val f = Fixture()

        f.viewModel.onAnswerChange(0, "7")

        assertEquals(7, f.state.answers[0])
        assertTrue(f.state.canGoNext)
    }

    @Test
    fun `문항 범위를 넘는 답변은 그 문항의 최댓값으로 맞춘다`() {
        val f = Fixture()

        f.viewModel.onAnswerChange(0, "99") // 식사 횟수 0..10
        f.viewModel.onAnswerChange(1, "99") // 수면 시간 0..24
        f.viewModel.onAnswerChange(4, "9999") // 미취업 기간 0..600

        assertEquals(10, f.state.answers[0])
        assertEquals(24, f.state.answers[1])
        assertEquals(600, f.state.answers[4])
    }

    @Test
    fun `숫자가 아니거나 비운 답변은 답하지 않은 것으로 돌아간다`() {
        val f = Fixture()
        f.viewModel.onAnswerChange(0, "5")

        f.viewModel.onAnswerChange(0, "")

        assertNull(f.state.answers[0])
        assertFalse(f.state.canGoNext)
    }

    @Test
    fun `없는 문항 번호로는 아무것도 바뀌지 않는다`() {
        val f = Fixture()
        val before = f.state

        f.viewModel.onAnswerChange(99, "5")
        f.viewModel.onAnswerChange(-1, "5")

        assertEquals(before, f.state)
    }

    // ===== 문항 이동 =====

    @Test
    fun `지금 문항에 답해야 다음 문항으로 넘어갈 수 있다`() {
        val f = Fixture()

        f.viewModel.goNext()
        assertEquals(0, f.state.currentPage)

        f.viewModel.onAnswerChange(0, "3")
        f.viewModel.goNext()
        assertEquals(1, f.state.currentPage)
    }

    @Test
    fun `이전 문항으로 돌아갈 수 있고 첫 문항에서는 더 돌아가지 않는다`() {
        val f = Fixture()
        f.viewModel.onAnswerChange(0, "3")
        f.viewModel.goNext()

        f.viewModel.goBack()
        assertEquals(0, f.state.currentPage)

        f.viewModel.goBack()
        assertEquals(0, f.state.currentPage)
    }

    @Test
    fun `마지막 문항에서는 더 넘어가지 않는다`() {
        val f = Fixture()

        f.answerAll()

        assertEquals(INIT_QUESTIONS.size - 1, f.state.currentPage)
        assertTrue(f.state.isLastPage)
        f.viewModel.goNext()
        assertEquals(INIT_QUESTIONS.size - 1, f.state.currentPage)
    }

    @Test
    fun `돌아가서 답을 지우면 다시 답하기 전에는 앞으로 못 간다`() {
        val f = Fixture()
        f.viewModel.onAnswerChange(0, "3")
        f.viewModel.goNext()
        f.viewModel.goBack()

        f.viewModel.onAnswerChange(0, "")
        f.viewModel.goNext()

        assertEquals(0, f.state.currentPage)
    }

    // ===== 제출 가능 조건 =====

    @Test
    fun `마지막 문항에 답했을 때만 제출할 수 있다`() {
        val f = Fixture()
        f.answerAll(listOf("3", "7", "4", "2", "12", ""))
        // 마지막 문항이 비어 있으면 제출 불가
        assertTrue(f.state.isLastPage)
        assertFalse(f.state.canSubmit)

        f.viewModel.onAnswerChange(5, "1")

        assertTrue(f.state.canSubmit)
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
    fun `제출에 성공하면 화면을 떠나므로 제출 중 상태를 유지한다`() = runTest {
        val f = Fixture()
        f.answerAll()

        f.viewModel.submit()

        assertTrue(f.state.isSubmitting)
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
    fun `제출할 수 없는 상태에서는 제출해도 아무 일도 일어나지 않는다`() = runTest {
        val f = Fixture()
        val events = collectEvents(f.viewModel.events)
        f.viewModel.onAnswerChange(0, "3")

        f.viewModel.submit()

        assertTrue(events.isEmpty())
        assertFalse(f.state.isSubmitting)
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
