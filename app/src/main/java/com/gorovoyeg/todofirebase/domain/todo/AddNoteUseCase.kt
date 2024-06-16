package com.gorovoyeg.todofirebase.domain.todo

import javax.inject.Inject

class AddNoteUseCase @Inject constructor(
    private val repository: TodoRepository
) {

    suspend operator fun invoke(noteEntity: NoteEntity) = repository.addNote(note = noteEntity)
}