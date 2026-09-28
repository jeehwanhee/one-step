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

    // ===== 닉네임 검증 (형식이 틀리면 가입 버튼이 눌리지 않는다) =====

    @Test
    fun `쓸 수 없는 문자가 있으면 오류가 표시되고 다른 입력이 모두 올바라도 가입할 수 없다`() {
        val f = Fixture()

        f.fillValid(nickname = "한 걸음!")

        assertEquals(NicknameError.InvalidCharacters, f.state.nicknameError)
        assertFalse(f.state.canSubmit)
    }

    // ===== 나이 입력과 검증 =====

    @Test
    fun `범위 밖의 나이로는 가입할 수 없다`() {
        listOf("1", (MIN_AGE - 1).toString(), (MAX_AGE + 1).toString(), "999").forEach { age ->
            val f = Fixture()
            f.fillValid(age = age)
            assertFalse("age=$age", f.state.canSubmit)
        }
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
