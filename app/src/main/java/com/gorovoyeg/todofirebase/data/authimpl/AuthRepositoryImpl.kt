package com.gorovoyeg.todofirebase.data.authimpl

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.gorovoyeg.todofirebase.domain.auth.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    val auth: FirebaseAuth,
    val database: FirebaseFirestore
) : AuthRepository {


    override suspend fun signUp(
        login: String,
        password: String
    ) {
        auth.createUserWithEmailAndPassword(login, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(
                        "AuthRepositoryImpl",
                        "signUp success ${auth.currentUser?.email.toString()}"
                    )
                } else {
                    Log.d("AuthRepositoryImpl", "signUp failure")
                }
            }
    }

    override suspend fun signIn(login: String, password: String) {
        auth.signInWithEmailAndPassword(login, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("AuthRepositoryImpl", "signIn success")
                } else {
                    Log.d("AuthRepositoryImpl", "signIn success")
                }
            }
    }

    override suspend fun signOut() {
        auth.signOut()
        Log.d("AuthRepositoryImpl", "signOut success")
    }

    override suspend fun checkCurrentUserisAuth(): Boolean {
        return auth.currentUser != null
    }
}