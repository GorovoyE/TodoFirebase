package com.gorovoyeg.todofirebase.domain.todo

class GetNoteListUseCase(
    private val repository: TodoRepository
) {

    suspend operator fun invoke() = repository.getNoteList()
}