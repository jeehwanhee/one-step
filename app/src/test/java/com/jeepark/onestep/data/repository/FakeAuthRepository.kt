package com.jeepark.onestep.data.repository

import android.content.Intent

/** 테스트용 가짜 AuthRepository. Firebase 없이 로그인 상태와 각 동작의 결과를 정해 둘 수 있다. */
class FakeAuthRepository(
    var uid: String? = "uid-1",
    var email: String? = "tester@example.com",
) : AuthRepository {

    /** [signInWithGoogle]이 돌려줄 결과. 성공하면 [uid]가 로그인된 것으로 남는다. */
    var signInResult: SignInResult = SignInResult.Success

    /** [deleteAuthAccount]가 돌려줄 결과. */
    var deleteAuthResult: Result<Unit> = Result.success(Unit)

    var signOutCount = 0
        private set
    var deleteAuthCount = 0
        private set

    override val currentUid: String? get() = uid

    override val currentEmail: String? get() = email

    override fun googleSignInIntent(): Intent = Intent()

    override suspend fun signInWithGoogle(data: Intent?): SignInResult = signInResult

    override fun signOut() {
        signOutCount++
        uid = null
        email = null
    }

    override suspend fun deleteAuthAccount(): Result<Unit> {
        deleteAuthCount++
        return deleteAuthResult
    }
}
