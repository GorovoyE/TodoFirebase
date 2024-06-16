package com.gorovoyeg.todofirebase.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gorovoyeg.todofirebase.data.authimpl.AuthRepositoryImpl
import com.gorovoyeg.todofirebase.domain.auth.AuthSignInUseCase
import com.gorovoyeg.todofirebase.domain.auth.AuthSignUpUseCase
import com.gorovoyeg.todofirebase.domain.auth.GetCurrentUserOnlineUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class AuthViewModel @Inject constructor(
    val signUpUseCase : AuthSignUpUseCase,
    val signInUseCase : AuthSignInUseCase,
    val getCurrentUserOnlineUseCase : GetCurrentUserOnlineUseCase
) : ViewModel() {


    private val _result = MutableLiveData<Boolean>()
    val result: LiveData<Boolean>
        get() = _result


    fun getSignUp(login: String, password: String) {
        if (validateData(login, password)) {
            viewModelScope.launch {
                signUpUseCase(login = login, password = password)
            }
        }
    }

    fun getSignIn(login: String, password: String) {
        if (validateData(login, password)) {
            _result.value = true
            viewModelScope.launch {
                signInUseCase(login = login, password = password)
            }
        } else {
            _result.value = false
        }
    }

    suspend fun checkCurrentUserIsOnline(): Boolean {
        return getCurrentUserOnlineUseCase()
    }

    private fun validateLogin(login: String): Boolean {
        return login.trim().isNotEmpty()
    }

    private fun validatePassword(password: String): Boolean {
        val wrongPassword = password.trim().length > 6
        return (password.isNotEmpty() && wrongPassword)
    }

    private fun validateData(login: String, password: String): Boolean {
        return (validateLogin(login) && validatePassword(password))
    }

}