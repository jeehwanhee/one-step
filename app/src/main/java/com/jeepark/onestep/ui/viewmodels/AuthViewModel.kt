package com.jeepark.onestep.ui.viewmodels

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.firestore
import com.jeepark.onestep.R

class AuthViewModel : ViewModel() {
    private val auth = Firebase.auth

    fun getGoogleSignInClient(context: Context): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(context, gso)
    }

    fun login(
        data: Intent?,
        onNewUser: () -> Unit,
        onExistingUser: () -> Unit,
        onError: () -> Unit
    ) {
        try {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)

            auth.signInWithCredential(credential)
                .addOnSuccessListener {
                    val uid = auth.currentUser?.uid ?: run { onError(); return@addOnSuccessListener }
                    // isNewUser 대신 Firestore 문서 존재 여부로 신규 유저 판별
                    Firebase.firestore.collection("users").document(uid)
                        .get(Source.SERVER)
                        .addOnSuccessListener { doc ->
                            if (doc.exists()) onExistingUser() else onNewUser()
                        }
                        .addOnFailureListener {
                            // 네트워크 실패를 신규 유저로 오판하지 않도록 onError 호출
                            onError()
                        }
                }
                .addOnFailureListener { e ->
                    onError()
                }
        } catch (e: ApiException) {
            if (e.statusCode != 12501) onError() // 12501 = 사용자가 직접 취소
        } catch (e: Exception) {
            onError()
        }
    }

    fun signOut(context: Context) {
        auth.signOut()
        getGoogleSignInClient(context).signOut()
    }

    fun deleteAccount(
        context: Context,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        val user = auth.currentUser ?: run { onFailure(); return }
        val uid  = user.uid
        Firebase.firestore.collection("users").document(uid)
            .delete()
            .addOnSuccessListener {
                // Firestore 삭제 성공 → Auth 계정 삭제 결과까지 확인
                user.delete()
                    .addOnSuccessListener {
                        auth.signOut()
                        getGoogleSignInClient(context).signOut()
                        onSuccess()
                    }
                    .addOnFailureListener {
                        // Auth 삭제 실패(예: 최근 로그인 필요) → 데이터는 이미 삭제됨
                        auth.signOut()
                        getGoogleSignInClient(context).signOut()
                        onFailure()
                    }
            }
            .addOnFailureListener { onFailure() }
    }
}
