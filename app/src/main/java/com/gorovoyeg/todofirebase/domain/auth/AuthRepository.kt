package com.gorovoyeg.todofirebase.domain.auth

interface AuthRepository {

    suspend fun signUp(login: String, password: String)

    suspend fun signIn(login: String, password: String)

    suspend fun signOut()

    suspend fun checkCurrentUserisAuth(): Boolean
}