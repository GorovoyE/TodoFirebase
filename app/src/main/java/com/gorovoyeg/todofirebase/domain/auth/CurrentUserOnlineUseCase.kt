package com.gorovoyeg.todofirebase.domain.auth

class CurrentUserOnlineUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(): Boolean = repository.checkCurrentUserOnline()
}