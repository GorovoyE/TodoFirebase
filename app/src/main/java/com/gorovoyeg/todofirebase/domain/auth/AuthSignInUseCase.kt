package com.gorovoyeg.todofirebase.domain.auth

import javax.inject.Inject

class AuthSignInUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(login: String, password: String) = repository.signIn(
        login = login,
        password = password
    )
}