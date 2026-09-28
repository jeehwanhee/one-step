package com.jeepark.onestep.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jeepark.onestep.data.model.INIT_QUESTIONS
import com.jeepark.onestep.data.model.buildInitQuestions
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

/**
 * 설문 화면 상태.
 * @param answers 문항별 답변. 아직 답하지 않은 문항은 null.
 */
data class InitQuestionUiState(
    val answers: List<Int?> = List(INIT_QUESTIONS.size) { null },
    val currentPage: Int = 0,
    val isSubmitting: Boolean = false,
) {
    val pageCount: Int get() = answers.size
    val isLastPage: Boolean get() = currentPage == pageCount - 1
    val canGoBack: Boolean get() = currentPage > 0

    /** 지금 문항에 답했는지. 답해야 다음으로 넘어가거나(마지막이면) 제출할 수 있다. */
    val canGoNext: Boolean get() = answers[currentPage] != null

    /** 마지막 문항이고 모든 문항에 답했으며 제출 중이 아닐 때만 제출할 수 있다. */
    val canSubmit: Boolean get() = isLastPage && answers.all { it != null } && !isSubmitting
}

sealed interface InitQuestionEvent {
    /** 제출에 성공했다. 메인으로 이동한다. */
    data object Completed : InitQuestionEvent

    /** 제출에 실패했다. 시작 화면으로 돌아간다. */
    data object Failed : InitQuestionEvent
}

class InitQuestionViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(InitQuestionUiState())
    val state: StateFlow<InitQuestionUiState> = _state.asStateFlow()

    private val _events = Channel<InitQuestionEvent>(Channel.BUFFERED)
    val events: Flow<InitQuestionEvent> = _events.receiveAsFlow()

    /** [page] 문항의 입력값이 바뀌었다. 숫자가 아니면 "답하지 않음", 숫자면 그 문항의 범위 안으로 맞춘다. */
    fun onAnswerChange(page: Int, text: String) {
        val spec = INIT_QUESTIONS.getOrNull(page) ?: return
        val answer = text.toIntOrNull()?.let { spec.coerce(it) }
        _state.update { current ->
            current.copy(answers = current.answers.toMutableList().also { it[page] = answer })
        }
    }

    /** 지금 문항에 답했고 마지막 문항이 아니면 다음 문항으로 간다. */
    fun goNext() = _state.update {
        if (it.canGoNext && !it.isLastPage) it.copy(currentPage = it.currentPage + 1) else it
    }

    fun goBack() = _state.update {
        if (it.canGoBack) it.copy(currentPage = it.currentPage - 1) else it
    }

    /** 답변을 제출한다. 제출할 수 없는 상태이거나 이미 제출 중이면 아무것도 하지 않는다. */
    fun submit() {
        val current = _state.value
        if (!current.canSubmit) return

        _state.update { it.copy(isSubmitting = true) }
        viewModelScope.launch {
            val questions = buildInitQuestions(current.answers.map { requireNotNull(it) })
            userRepository.saveInitQuestions(questions).fold(
                // 성공하면 화면을 떠나므로 제출 중 상태는 그대로 둔다
                onSuccess = { _events.trySend(InitQuestionEvent.Completed) },
                onFailure = {
                    _state.update { state -> state.copy(isSubmitting = false) }
                    _events.trySend(InitQuestionEvent.Failed)
                },
            )
        }
    }

    companion object {
        /** 앱 컨테이너의 실제 구현체를 연결한 ViewModel 팩토리. */
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer { InitQuestionViewModel(oneStepApp().container.userRepository) }
        }
    }
}
