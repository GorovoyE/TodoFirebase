package com.gorovoyeg.todofirebase.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gorovoyeg.todofirebase.data.authimpl.AuthRepositoryImpl
import com.gorovoyeg.todofirebase.domain.auth.AuthSignInUseCase
import com.gorovoyeg.todofirebase.domain.auth.AuthSignUpUseCase
import com.gorovoyeg.todofirebase.domain.auth.CurrentUserOnlineUseCase
import com.gorovoyeg.todofirebase.domain.auth.SignOutUseCase
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val repository = AuthRepositoryImpl()
    private val signUpUseCase = AuthSignUpUseCase(repository)
    private val signInUseCase = AuthSignInUseCase(repository)
    private val signOutUseCase = SignOutUseCase(repository)
    private val currentUserOnlineUseCase = CurrentUserOnlineUseCase(repository)
    private val _isUserSignIn = MutableLiveData<Boolean>()
    val isUserSignIn: LiveData<Boolean> = _isUserSignIn


    init {
        viewModelScope.launch {
            _isUserSignIn.value = currentUserOnlineUseCase()
        }
    }

    fun signOut() {
        viewModelScope.launch {
            signOutUseCase.invoke()
        }
    }

    fun getSignUp(login: String, password: String) {
        viewModelScope.launch {
            signUpUseCase(login = login, password = password)
        }
    }

    fun getSignIn(login: String, password: String) {
        viewModelScope.launch {
            signInUseCase(login = login, password = password)
        }
    }
}