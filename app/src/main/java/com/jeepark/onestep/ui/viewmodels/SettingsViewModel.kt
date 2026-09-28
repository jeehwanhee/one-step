package com.jeepark.onestep.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jeepark.onestep.data.repository.AccountService
import com.jeepark.onestep.data.repository.AuthRepository
import com.jeepark.onestep.data.repository.DeleteResult
import com.jeepark.onestep.data.repository.SettingsRepository
import com.jeepark.onestep.data.repository.UserRepository
import com.jeepark.onestep.oneStepApp
import com.jeepark.onestep.util.NotificationScheduler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SettingsUiState(
    val nickname: String = "",
    val email: String = "",
    val notificationsEnabled: Boolean = true,
    val showLogoutDialog: Boolean = false,
    val showDeleteDialog: Boolean = false,
    val isDeleting: Boolean = false,
)

sealed interface SettingsEvent {
    /** 로그아웃했거나 계정이 지워져 더는 이 계정으로 앱을 쓸 수 없다. 시작 화면으로 돌아간다. */
    data object LeftAccount : SettingsEvent
}

class SettingsViewModel(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
    private val settingsRepository: SettingsRepository,
    private val notificationScheduler: NotificationScheduler,
    private val accountService: AccountService,
) : ViewModel() {

    private val _state = MutableStateFlow(
        SettingsUiState(
            email = authRepository.currentEmail.orEmpty(),
            notificationsEnabled = settingsRepository.notificationsEnabled,
        )
    )
    val state: StateFlow<SettingsUiState> = _state.asStateFlow()

    private val _events = Channel<SettingsEvent>(Channel.BUFFERED)
    val events: Flow<SettingsEvent> = _events.receiveAsFlow()

    init {
        viewModelScope.launch {
            userRepository.getUser().onSuccess { user ->
                _state.update { it.copy(nickname = user.nickname) }
            }
        }
    }

    /**
     * 알림 스위치를 바꿨다. 이 기기의 설정, 서버의 알림 동의 여부, 알림 예약을 함께 맞춘다.
     * 서버 저장은 확인을 기다리지 않는다(오프라인이어도 스위치는 바로 반영된다).
     */
    fun onNotificationsToggled(enabled: Boolean) {
        _state.update { it.copy(notificationsEnabled = enabled) }
        settingsRepository.setNotificationsEnabled(enabled)
        authRepository.currentUid?.let { uid ->
            viewModelScope.launch { userRepository.updateNotificationAgreed(uid, enabled) }
        }
        if (enabled) notificationScheduler.schedule() else notificationScheduler.cancel()
    }

    fun showLogoutDialog() = _state.update { it.copy(showLogoutDialog = true) }

    fun dismissLogoutDialog() = _state.update { it.copy(showLogoutDialog = false) }

    fun confirmLogout() {
        accountService.signOut()
        _state.update { it.copy(showLogoutDialog = false) }
        _events.trySend(SettingsEvent.LeftAccount)
    }

    fun showDeleteDialog() = _state.update { it.copy(showDeleteDialog = true) }

    fun dismissDeleteDialog() = _state.update { it.copy(showDeleteDialog = false) }

    /**
     * 계정을 삭제한다. 이미 삭제 중이면 무시한다.
     * 데이터가 지워졌다면(인증 삭제만 실패했어도) 로그아웃된 상태이므로 시작 화면으로 돌아가고,
     * 데이터 삭제 자체가 실패했다면 아무것도 바뀌지 않았으니 설정 화면에 남는다.
     */
    fun confirmDelete() {
        if (_state.value.isDeleting) return
        _state.update { it.copy(isDeleting = true) }
        viewModelScope.launch {
            val result = accountService.deleteAccount()
            _state.update { it.copy(showDeleteDialog = false, isDeleting = false) }
            if (result != DeleteResult.Failed) _events.trySend(SettingsEvent.LeftAccount)
        }
    }

    companion object {
        /** 앱 컨테이너의 실제 구현체를 연결한 ViewModel 팩토리. */
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val container = oneStepApp().container
                SettingsViewModel(
                    authRepository = container.authRepository,
                    userRepository = container.userRepository,
                    settingsRepository = container.settingsRepository,
                    notificationScheduler = container.notificationScheduler,
                    accountService = container.accountService,
                )
            }
        }
    }
}
