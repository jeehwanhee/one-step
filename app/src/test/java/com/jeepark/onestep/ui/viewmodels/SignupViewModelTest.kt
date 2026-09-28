package com.jeepark.onestep.ui.viewmodels

import com.jeepark.onestep.MainDispatcherRule
import com.jeepark.onestep.collectEvents
import com.jeepark.onestep.data.model.Gender
import com.jeepark.onestep.data.model.MAX_AGE
import com.jeepark.onestep.data.model.MIN_AGE
import com.jeepark.onestep.data.repository.FakeUserRepository
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class SignupViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private class Fixture(gate: CompletableDeferred<Unit>? = null) {
        val users = FakeUserRepository().also { it.writeGate = gate }
        val viewModel = SignupViewModel(users)
        val state get() = viewModel.state.value

        /** 가입에 필요한 모든 입력을 올바르게 채운다. */
        fun fillValid(nickname: String = "한걸음", age: String = "25") = viewModel.run {
            onNicknameChange(nickname)
            onAgeChange(age)
            onTermsToggled()
            onPrivacyToggled()
        }
    }

    // ===== 입력 칸이 나타나는 순서 =====

    @Test
    fun `처음에는 닉네임만 입력할 수 있다`() {
        val f = Fixture()

        assertFalse(f.state.showAgeField)
        assertFalse(f.state.showGenderField)
        assertFalse(f.state.canSubmit)
    }

    @Test
    fun `닉네임을 입력하면 나이 칸이 나타나고 나이까지 입력하면 성별 선택이 나타난다`() {
        val f = Fixture()

        f.viewModel.onNicknameChange("한걸음")
        assertTrue(f.state.showAgeField)
        assertFalse(f.state.showGenderField)

        f.viewModel.onAgeChange("25")
        assertTrue(f.state.showGenderField)
    }

    // ===== 닉네임 검증 (형식이 틀리면 가입 버튼이 눌리지 않는다) =====

    @Test
    fun `쓸 수 없는 문자가 있으면 오류가 표시되고 다른 입력이 모두 올바라도 가입할 수 없다`() {
        val f = Fixture()

        f.fillValid(nickname = "한 걸음!")

        assertEquals(NicknameError.InvalidCharacters, f.state.nicknameError)
        assertFalse(f.state.canSubmit)
    }

    @Test
    fun `닉네임을 고치면 오류가 사라지고 가입할 수 있게 된다`() {
        val f = Fixture()
        f.fillValid(nickname = "한 걸음!")

        f.viewModel.onNicknameChange("한걸음")

        assertNull(f.state.nicknameError)
        assertTrue(f.state.canSubmit)
    }

    @Test
    fun `비어 있는 닉네임은 오류는 아니지만 가입할 수 없다`() {
        val f = Fixture()

        assertNull(f.state.nicknameError)
        assertFalse(f.state.canSubmit)
    }

    // ===== 나이 입력과 검증 =====

    @Test
    fun `숫자가 아니거나 0이거나 너무 큰 입력은 입력하지 않은 것으로 본다`() {
        val f = Fixture()

        listOf("", "0", "abc", "99999999999").forEach { text ->
            f.viewModel.onAgeChange("25")
            f.viewModel.onAgeChange(text)
            assertNull("text=$text", f.state.age)
        }
    }

    @Test
    fun `나이를 입력하면 숫자로 저장된다`() {
        val f = Fixture()

        f.viewModel.onAgeChange("25")

        assertEquals(25, f.state.age)
    }

    @Test
    fun `범위 밖의 나이로는 가입할 수 없다`() {
        listOf("1", (MIN_AGE - 1).toString(), (MAX_AGE + 1).toString(), "999").forEach { age ->
            val f = Fixture()
            f.fillValid(age = age)
            assertFalse("age=$age", f.state.canSubmit)
        }
    }

    @Test
    fun `범위의 경계 나이로는 가입할 수 있다`() {
        listOf(MIN_AGE, MAX_AGE).forEach { age ->
            val f = Fixture()
            f.fillValid(age = age.toString())
            assertTrue("age=$age", f.state.canSubmit)
        }
    }

    // ===== 약관 =====

    @Test
    fun `이용약관과 개인정보 처리방침 둘 다 동의해야 가입할 수 있다`() {
        val f = Fixture()
        f.viewModel.onNicknameChange("한걸음")
        f.viewModel.onAgeChange("25")
        assertFalse(f.state.canSubmit)

        f.viewModel.onTermsToggled()
        assertFalse(f.state.canSubmit)

        f.viewModel.onPrivacyToggled()
        assertTrue(f.state.canSubmit)

        f.viewModel.onTermsToggled() // 동의 취소
        assertFalse(f.state.canSubmit)
    }

    @Test
    fun `성별은 기본이 남자이고 바꿀 수 있다`() {
        val f = Fixture()
        assertEquals(Gender.MALE, f.state.gender)

        f.viewModel.onGenderSelected(Gender.FEMALE)

        assertEquals(Gender.FEMALE, f.state.gender)
    }

    // ===== 제출 =====

    @Test
    fun `가입하면 입력한 정보가 저장되고 완료 이벤트가 나온다`() = runTest {
        val f = Fixture()
        val events = collectEvents(f.viewModel.events)
        f.fillValid(nickname = "한걸음", age = "31")
        f.viewModel.onGenderSelected(Gender.FEMALE)

        f.viewModel.submit()

        assertEquals(listOf<SignupEvent>(SignupEvent.Completed), events)
        val saved = f.users.user!!
        assertEquals("한걸음", saved.nickname)
        assertEquals(31, saved.age)
        assertEquals(Gender.FEMALE.storedValue, saved.gender)
    }

    @Test
    fun `저장에 성공하면 화면을 떠나므로 저장 중 상태를 유지해 버튼이 다시 눌리지 않는다`() = runTest {
        val f = Fixture()
        f.fillValid()

        f.viewModel.submit()

        assertTrue(f.state.isSubmitting)
        assertFalse(f.state.canSubmit)
    }

    @Test
    fun `저장에 실패하면 실패 이벤트가 나오고 다시 시도할 수 있다`() = runTest {
        val f = Fixture()
        f.users.shouldFail = true
        f.users.failureMessage = "네트워크 오류"
        val events = collectEvents(f.viewModel.events)
        f.fillValid()

        f.viewModel.submit()

        assertEquals(listOf<SignupEvent>(SignupEvent.Failed("네트워크 오류")), events)
        assertFalse(f.state.isSubmitting)
        assertTrue(f.state.canSubmit)
    }

    @Test
    fun `가입할 수 없는 상태에서는 제출해도 아무 일도 일어나지 않는다`() = runTest {
        val f = Fixture()
        val events = collectEvents(f.viewModel.events)
        f.fillValid(nickname = "한 걸음!")

        f.viewModel.submit()

        assertTrue(events.isEmpty())
        assertNull(f.users.user)
        assertFalse(f.state.isSubmitting)
    }

    @Test
    fun `저장 중에 다시 눌러도 한 번만 저장된다`() = runTest {
        val gate = CompletableDeferred<Unit>()
        val f = Fixture(gate)
        val events = collectEvents(f.viewModel.events)
        f.fillValid()

        f.viewModel.submit()
        f.viewModel.submit()
        f.viewModel.submit()
        assertTrue(events.isEmpty()) // 서버 응답 전
        assertTrue(f.state.isSubmitting)

        gate.complete(Unit)

        assertEquals(listOf<SignupEvent>(SignupEvent.Completed), events)
    }
}
