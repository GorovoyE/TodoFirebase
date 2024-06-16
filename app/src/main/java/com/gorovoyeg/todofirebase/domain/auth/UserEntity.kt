package com.gorovoyeg.todofirebase.domain.auth

import javax.inject.Inject

data class UserEntity @Inject constructor(
    val userName: String
)