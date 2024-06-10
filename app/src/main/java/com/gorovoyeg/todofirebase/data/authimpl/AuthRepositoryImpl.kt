package com.gorovoyeg.todofirebase.data.authimpl

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.gorovoyeg.todofirebase.domain.auth.AuthRepository

class AuthRepositoryImpl : AuthRepository {
    private val auth = Firebase.auth
    private val currentUser = auth.currentUser
    override suspend fun signUp(login: String, password: String) {
        if (currentUser == null) {
            auth.createUserWithEmailAndPassword(login, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("TAG", "signUp: is successful${task.isSuccessful}")
                    } else {
                        Log.d("TAG", "signUp: ${task.exception}")
                    }
                }
        } else {
            Log.d("TAG", "signUp: else email ${currentUser.email}")
        }
    }

    override suspend fun signIn(login: String, password: String) {
        if (currentUser != null) {
            auth.signInWithEmailAndPassword(login, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("TAG", "signIn: ${task.isSuccessful}")
                    } else {
                        Log.d("TAG", "signIn: ${task.exception}")
                    }
                }
        }
    }

    override suspend fun signOut() {
        auth.signOut()
        Log.d("TAG", "signOut: ${currentUser}")
    }

    override suspend fun checkCurrentUserOnline(): Boolean {
        return currentUser != null
    }
}