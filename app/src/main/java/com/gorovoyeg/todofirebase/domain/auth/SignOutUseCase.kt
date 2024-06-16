package com.gorovoyeg.todofirebase.domain.auth

import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke() = repository.signOut()
}