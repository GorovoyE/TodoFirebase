package com.gorovoyeg.todofirebase.presentation.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gorovoyeg.todofirebase.data.todoimpl.TodoRepositoryImpl
import com.gorovoyeg.todofirebase.domain.todo.AddNoteUseCase
import com.gorovoyeg.todofirebase.domain.todo.NoteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    val addNoteUseCase: AddNoteUseCase
) : ViewModel() {

    fun addNote(noteEntity: NoteEntity) {
        viewModelScope.launch {
            addNoteUseCase.invoke(noteEntity)
        }
    }
}