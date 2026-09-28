package com.jeepark.onestep.ui.screens.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jeepark.onestep.data.repository.AuthRepository
import com.jeepark.onestep.data.repository.UserRepository
import com.jeepark.onestep.domain.model.assessmentState
import com.jeepark.onestep.domain.rules.needsAssessment
import com.jeepark.onestep.oneStepApp
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/** 앱을 켰을 때 스플래시 다음에 보여줄 화면. */
enum class StartDestination { Auth, InitQuestion, Main }

/** 스플래시 화면을 최소한 보여주는 시간. */
const val SPLASH_DELAY_MILLIS = 1500L

/**
 * 시작 화면(스플래시)의 라우팅. 로그인 여부와 서버의 사용자 상태를 보고 다음 화면을 한 번만 알려준다.
 *
 * - 로그인하지 않았다 → [StartDestination.Auth]
 * - 사용자 문서가 없거나 서버 조회에 실패했다 → [StartDestination.Auth]
 * - 설문이 필요하다 → [StartDestination.InitQuestion]
 * - 그 외 → [StartDestination.Main]
 */
class InitViewModel(
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository,
    private val splashDelayMillis: Long = SPLASH_DELAY_MILLIS,
) : ViewModel() {

    private val _destination = Channel<StartDestination>(Channel.BUFFERED)

    /** 다음 화면. 한 번만 전달되고, 화면이 회전으로 다시 그려져도 중복 이동하지 않는다. */
    val destination: Flow<StartDestination> = _destination.receiveAsFlow()

    init {
        viewModelScope.launch { _destination.send(resolveDestination()) }
    }

    private suspend fun resolveDestination(): StartDestination {
        val uid = authRepository.currentUid
        delay(splashDelayMillis)

        if (uid == null) return StartDestination.Auth

        return userRepository.getUserFromServer(uid).fold(
            onSuccess = { user ->
                when {
                    user == null          -> StartDestination.Auth
                    needsAssessment(user.assessmentState()) -> StartDestination.InitQuestion
                    else                  -> StartDestination.Main
                }
            },
            onFailure = { StartDestination.Auth },
        )
    }

    companion object {
        /** 앱 컨테이너의 실제 구현체를 연결한 ViewModel 팩토리. */
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val container = oneStepApp().container
                InitViewModel(container.userRepository, container.authRepository)
            }
        }
    }
}
