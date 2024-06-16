package com.gorovoyeg.todofirebase.domain.todo

import javax.inject.Inject

class GetNoteListUseCase @Inject constructor(
    private val repository: TodoRepository
) {

    suspend operator fun invoke() = repository.getNoteList()
}