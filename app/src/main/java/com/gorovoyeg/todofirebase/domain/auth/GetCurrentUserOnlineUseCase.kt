package com.gorovoyeg.todofirebase.domain.auth

import javax.inject.Inject

class GetCurrentUserOnlineUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(): Boolean = repository.checkCurrentUserisAuth()
}