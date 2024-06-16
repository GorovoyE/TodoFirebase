package com.gorovoyeg.todofirebase.domain.todo

import javax.inject.Inject

class GetNoteUseCase @Inject constructor(
    private val repository: TodoRepository
) {

    suspend operator fun invoke(id: String) = repository.getNote(id)
}