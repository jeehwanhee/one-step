package com.jeepark.onestep.ui.screens.auth

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jeepark.onestep.data.repository.AuthRepository
import com.jeepark.onestep.data.repository.SignInResult
import com.jeepark.onestep.data.repository.UserRepository
import com.jeepark.onestep.oneStepApp
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/** 구글 로그인 뒤 화면이 할 일. */
sealed interface LoginResult {
    /** 로그인은 됐지만 서버에 사용자 문서가 없다. 가입 화면으로 간다. */
    data object NewUser : LoginResult

    /** 로그인했고 사용자 문서가 있다. 메인으로 간다. */
    data object ExistingUser : LoginResult

    /** 로그인이나 사용자 확인에 실패했다. */
    data object Error : LoginResult
}

class AuthViewModel(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _results = Channel<LoginResult>(Channel.BUFFERED)

    /** 로그인 결과. 한 번만 전달된다. 사용자가 로그인 창을 직접 닫은 경우에는 아무것도 전달되지 않는다. */
    val results: Flow<LoginResult> = _results.receiveAsFlow()

    fun googleSignInIntent(): Intent = authRepository.googleSignInIntent()

    /** 구글 로그인 창이 돌려준 [data]를 처리한다. 로그인에 성공하면 서버의 사용자 문서 유무로 기존/신규 사용자를 가른다. */
    fun onSignInResult(data: Intent?) {
        viewModelScope.launch {
            when (authRepository.signInWithGoogle(data)) {
                SignInResult.Cancelled -> Unit
                SignInResult.Failed -> _results.send(LoginResult.Error)
                SignInResult.Success -> _results.send(resolveLoggedInUser())
            }
        }
    }

    private suspend fun resolveLoggedInUser(): LoginResult {
        val uid = authRepository.currentUid ?: return LoginResult.Error
        // isNewUser 대신 Firestore 문서 존재 여부로 신규 유저 판별
        return userRepository.getUserFromServer(uid).fold(
            onSuccess = { user -> if (user != null) LoginResult.ExistingUser else LoginResult.NewUser },
            // 네트워크 실패를 신규 유저로 오판하지 않도록 오류로 처리
            onFailure = { LoginResult.Error },
        )
    }

    companion object {
        /** 앱 컨테이너의 실제 구현체를 연결한 ViewModel 팩토리. */
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val container = oneStepApp().container
                AuthViewModel(container.authRepository, container.userRepository)
            }
        }
    }
}
