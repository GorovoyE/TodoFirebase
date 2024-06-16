package com.gorovoyeg.todofirebase.domain.auth

import javax.inject.Inject

class AuthSignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(login: String, password: String) = authRepository.signUp(
        login = login,
        password = password
    )
}