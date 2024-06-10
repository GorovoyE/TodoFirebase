package com.gorovoyeg.todofirebase.domain.auth

class AuthSignUpUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(login: String, password: String) = authRepository.signUp(
        login = login,
        password = password
    )
}