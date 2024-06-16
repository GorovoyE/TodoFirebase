package com.gorovoyeg.todofirebase.domain.todo

import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    private val repository: TodoRepository
) {

    suspend operator fun invoke(note: NoteEntity) = repository.deleteNote(note)
}