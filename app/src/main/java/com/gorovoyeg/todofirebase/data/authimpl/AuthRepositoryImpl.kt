package com.gorovoyeg.todofirebase.data.authimpl

import com.gorovoyeg.todofirebase.domain.auth.AuthRepository
import com.gorovoyeg.todofirebase.domain.auth.UserEntity

class AuthRepositoryImpl: AuthRepository {
    override suspend fun signUp(login: String, password: String): UserEntity {
        TODO("Not yet implemented")
    }

    override suspend fun signIn(login: String, password: String): Boolean {
        TODO("Not yet implemented")
    }
}