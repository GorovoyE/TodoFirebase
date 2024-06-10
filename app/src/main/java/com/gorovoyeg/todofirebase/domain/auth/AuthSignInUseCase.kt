package com.gorovoyeg.todofirebase.domain.auth

class AuthSignInUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(login: String, password: String) = repository.signIn(
        login = login,
        password = password
    )
}