package com.gorovoyeg.todofirebase.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gorovoyeg.todofirebase.domain.auth.SignOutUseCase
import com.gorovoyeg.todofirebase.domain.todo.GetNoteListUseCase
import com.gorovoyeg.todofirebase.domain.todo.NoteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainListViewModel @Inject constructor(
    val getNoteListUseCase : GetNoteListUseCase,
    val signOutUseCase : SignOutUseCase
) : ViewModel() {


    private val _todoList = MutableLiveData<List<NoteEntity>>()
    val todoList: LiveData<List<NoteEntity>> = _todoList


    fun signOut() {
        viewModelScope.launch {
            signOutUseCase.invoke()
        }
    }
}