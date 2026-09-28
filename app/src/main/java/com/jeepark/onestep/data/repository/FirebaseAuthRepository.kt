package com.jeepark.onestep.data.repository

import android.app.Application
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.jeepark.onestep.R
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.tasks.await

/** Firebase Auth + 구글 로그인 구현. [app]을 들고 있어서 메서드마다 Context를 받을 필요가 없다. */
class FirebaseAuthRepository(private val app: Application) : AuthRepository {

    private val auth = Firebase.auth

    override val currentUid: String? get() = auth.currentUser?.uid

    override val currentEmail: String? get() = auth.currentUser?.email

    private fun googleClient(): GoogleSignInClient {
        val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(app.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(app, options)
    }

    override fun googleSignInIntent(): Intent = googleClient().signInIntent

    override suspend fun signInWithGoogle(data: Intent?): SignInResult = try {
        val account = GoogleSignIn.getSignedInAccountFromIntent(data).getResult(ApiException::class.java)
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).await()
        if (auth.currentUser != null) SignInResult.Success else SignInResult.Failed
    } catch (e: ApiException) {
        if (e.statusCode == SIGN_IN_CANCELLED) SignInResult.Cancelled else SignInResult.Failed
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        SignInResult.Failed
    }

    override fun signOut() {
        auth.signOut()
        googleClient().signOut()
    }

    override suspend fun deleteAuthAccount(): Result<Unit> {
        val user = auth.currentUser ?: return Result.failure(IllegalStateException("로그인된 사용자가 없습니다"))
        return try {
            user.delete().await()
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private companion object {
        /** GoogleSignInStatusCodes.SIGN_IN_CANCELLED — 사용자가 로그인 화면을 직접 닫았다. */
        const val SIGN_IN_CANCELLED = 12501
    }
}
