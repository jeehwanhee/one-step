package com.jeepark.onestep.ui.viewmodels

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jeepark.onestep.data.repository.AccountService
import com.jeepark.onestep.data.repository.AuthRepository
import com.jeepark.onestep.data.repository.DeleteResult
import com.jeepark.onestep.data.repository.SignInResult
import com.jeepark.onestep.data.repository.UserRepository
import com.jeepark.onestep.oneStepApp
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
    private val accountService: AccountService,
) : ViewModel() {

    val currentUid: String? get() = authRepository.currentUid

    val currentEmail: String? get() = authRepository.currentEmail

    fun googleSignInIntent(): Intent = authRepository.googleSignInIntent()

    /**
     * 구글 로그인 결과 처리. 로그인에 성공하면 서버에 사용자 문서가 있는지로 기존/신규 사용자를 가른다.
     * 사용자가 로그인 화면을 직접 닫은 경우에는 아무 콜백도 부르지 않는다.
     */
    fun login(
        data: Intent?,
        onNewUser: () -> Unit,
        onExistingUser: () -> Unit,
        onError: () -> Unit
    ) {
        viewModelScope.launch {
            when (authRepository.signInWithGoogle(data)) {
                SignInResult.Cancelled -> Unit
                SignInResult.Failed -> onError()
                SignInResult.Success -> {
                    val uid = authRepository.currentUid ?: run { onError(); return@launch }
                    // isNewUser 대신 Firestore 문서 존재 여부로 신규 유저 판별
                    userRepository.getUserFromServer(
                        uid = uid,
                        onResult = { user -> if (user != null) onExistingUser() else onNewUser() },
                        // 네트워크 실패를 신규 유저로 오판하지 않도록 onError 호출
                        onError = { onError() }
                    )
                }
            }
        }
    }

    fun signOut() = accountService.signOut()

    /** 계정 삭제. 결과에 따라 화면이 어디로 갈지는 호출한 쪽이 정한다([DeleteResult] 참고). */
    fun deleteAccount(onResult: (DeleteResult) -> Unit) {
        viewModelScope.launch { onResult(accountService.deleteAccount()) }
    }

    companion object {
        /** 앱 컨테이너의 실제 구현체를 연결한 ViewModel 팩토리. */
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val container = oneStepApp().container
                AuthViewModel(
                    authRepository = container.authRepository,
                    userRepository = container.userRepository,
                    accountService = container.accountService,
                )
            }
        }
    }
}
