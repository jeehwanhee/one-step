package com.jeepark.onestep.data.repository

import android.content.Intent

/**
 * 로그인 상태와 인증 계정 접근. ViewModel과 저장소는 Firebase Auth를 직접 만지지 않고 이 인터페이스만 쓴다.
 * 테스트에서는 FakeAuthRepository로 교체할 수 있다.
 */
interface AuthRepository {
    /** 로그인한 사용자의 uid. 로그인하지 않았으면 null. */
    val currentUid: String?

    /** 로그인한 사용자의 이메일. 로그인하지 않았거나 이메일이 없으면 null. */
    val currentEmail: String?

    /** 구글 로그인 화면을 띄우는 인텐트. */
    fun googleSignInIntent(): Intent

    /** 구글 로그인 화면이 돌려준 [data]로 Firebase에 로그인한다. */
    suspend fun signInWithGoogle(data: Intent?): SignInResult

    /** Firebase와 구글 계정에서 모두 로그아웃한다. */
    fun signOut()

    /** Firebase 인증 계정을 삭제한다(사용자 데이터는 삭제하지 않는다). 최근 로그인이 필요한 경우 등에 실패할 수 있다. */
    suspend fun deleteAuthAccount(): Result<Unit>
}

sealed interface SignInResult {
    /** 로그인에 성공했다. uid는 [AuthRepository.currentUid]로 읽는다. */
    data object Success : SignInResult

    /** 사용자가 구글 로그인 화면을 직접 닫았다(오류가 아니다). */
    data object Cancelled : SignInResult

    data object Failed : SignInResult
}
