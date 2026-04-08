package com.jeepark.onestep.ui.viewmodels

import android.content.Context
import android.content.Intent
import android.util.Log
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
                            Log.e("Auth", "Firestore 조회 실패: ${it.message}")
                            onNewUser()
                        }
                }
                .addOnFailureListener { e ->
                    Log.e("Auth", "Firebase 로그인 실패: ${e.message}")
                    onError()
                }
        } catch (e: ApiException) {
            Log.e("Auth", "Google 로그인 취소 또는 실패: ${e.statusCode}")
            if (e.statusCode != 12501) onError() // 12501 = 사용자가 직접 취소
        } catch (e: Exception) {
            Log.e("Auth", "로그인 예외: ${e.message}")
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
                // Firestore 삭제 성공 → Auth 계정 삭제 시도 후 결과와 관계없이 로그아웃 처리
                user.delete()
                auth.signOut()
                getGoogleSignInClient(context).signOut()
                onSuccess()
            }
            .addOnFailureListener { onFailure() }
    }
}
