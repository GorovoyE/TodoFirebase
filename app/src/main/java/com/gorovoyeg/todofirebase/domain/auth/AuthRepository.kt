package com.gorovoyeg.todofirebase.domain.auth

interface AuthRepository {

    suspend fun signUp(login: String, password: String): UserEntity

    suspend fun signIn(login: String, password: String): Boolean
}