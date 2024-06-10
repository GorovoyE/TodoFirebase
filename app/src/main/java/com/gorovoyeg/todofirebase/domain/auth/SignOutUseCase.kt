package com.gorovoyeg.todofirebase.domain.auth

class SignOutUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke() = repository.signOut()
}