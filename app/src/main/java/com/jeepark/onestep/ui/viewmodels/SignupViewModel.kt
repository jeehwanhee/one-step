package com.jeepark.onestep.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jeepark.onestep.data.model.Gender
import com.jeepark.onestep.data.model.hasInvalidNicknameCharacters
import com.jeepark.onestep.data.model.isValidAge
import com.jeepark.onestep.data.model.isValidNickname
import com.jeepark.onestep.data.repository.UserRepository
import com.jeepark.onestep.oneStepApp
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class NicknameError { InvalidCharacters }

/**
 * 회원가입 화면 상태.
 * @param age 입력한 나이. 입력하지 않았으면 null.
 */
data class SignupUiState(
    val nickname: String = "",
    val age: Int? = null,
    val gender: Gender = Gender.MALE,
    val agreeTerms: Boolean = false,
    val agreePrivacy: Boolean = false,
    val isSubmitting: Boolean = false,
) {
    /** 닉네임에 쓸 수 없는 문자가 있으면 그 이유. 문제가 없으면 null. */
    val nicknameError: NicknameError?
        get() = if (hasInvalidNicknameCharacters(nickname)) NicknameError.InvalidCharacters else null

    /** 닉네임을 입력하기 시작해야 나이 입력칸이 나타난다. */
    val showAgeField: Boolean get() = nickname.isNotEmpty()

    /** 나이까지 입력해야 성별 선택이 나타난다. */
    val showGenderField: Boolean get() = nickname.isNotEmpty() && age != null

    /** 닉네임·나이가 올바르고 약관 두 개에 모두 동의했으며 저장 중이 아닐 때만 가입할 수 있다. */
    val canSubmit: Boolean
        get() = isValidNickname(nickname) && isValidAge(age) && agreeTerms && agreePrivacy && !isSubmitting
}

sealed interface SignupEvent {
    /** 프로필 저장에 성공했다. 설문으로 이동한다. */
    data object Completed : SignupEvent

    /** 프로필 저장에 실패했다. */
    data class Failed(val message: String?) : SignupEvent
}

class SignupViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(SignupUiState())
    val state: StateFlow<SignupUiState> = _state.asStateFlow()

    private val _events = Channel<SignupEvent>(Channel.BUFFERED)
    val events: Flow<SignupEvent> = _events.receiveAsFlow()

    fun onNicknameChange(nickname: String) = _state.update { it.copy(nickname = nickname) }

    /** 입력한 글자로 나이를 바꾼다. 숫자가 아니거나 0이면 "입력하지 않음"으로 본다. */
    fun onAgeChange(text: String) =
        _state.update { it.copy(age = text.toIntOrNull()?.takeIf { age -> age > 0 }) }

    fun onGenderSelected(gender: Gender) = _state.update { it.copy(gender = gender) }

    fun onTermsToggled() = _state.update { it.copy(agreeTerms = !it.agreeTerms) }

    fun onPrivacyToggled() = _state.update { it.copy(agreePrivacy = !it.agreePrivacy) }

    /** 프로필을 저장한다. 가입할 수 없는 상태이거나 이미 저장 중이면 아무것도 하지 않는다. */
    fun submit() {
        val current = _state.value
        val age = current.age
        if (!current.canSubmit || age == null) return

        _state.update { it.copy(isSubmitting = true) }
        viewModelScope.launch {
            userRepository.saveInitUser(current.nickname, age, current.gender).fold(
                // 성공하면 화면을 떠나므로 저장 중 상태는 그대로 둔다(버튼 중복 누름 방지)
                onSuccess = { _events.trySend(SignupEvent.Completed) },
                onFailure = { e ->
                    _state.update { it.copy(isSubmitting = false) }
                    _events.trySend(SignupEvent.Failed(e.message))
                },
            )
        }
    }

    companion object {
        /** 앱 컨테이너의 실제 구현체를 연결한 ViewModel 팩토리. */
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer { SignupViewModel(oneStepApp().container.userRepository) }
        }
    }
}
