package com.gorovoyeg.todofirebase.domain.todo

class AddNoteUseCase(
    private val repository: TodoRepository
) {

    suspend operator fun invoke(noteEntity: NoteEntity) = repository.addNote(note = noteEntity)
}