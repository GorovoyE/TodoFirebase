package com.gorovoyeg.todofirebase.domain.todo

import com.gorovoyeg.todofirebase.domain.auth.AuthRepository
import javax.inject.Inject

class UpdateNoteUseCase @Inject constructor(
    private val repository: TodoRepository
) {

    suspend operator fun invoke(note: NoteEntity) = repository.updateNote(note)
}