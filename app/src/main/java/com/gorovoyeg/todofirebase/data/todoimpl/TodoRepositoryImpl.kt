package com.gorovoyeg.todofirebase.data.todoimpl

import com.gorovoyeg.todofirebase.domain.todo.NoteEntity
import com.gorovoyeg.todofirebase.domain.todo.TodoRepository

class TodoRepositoryImpl: TodoRepository {
    override suspend fun addNote(note: NoteEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun getNoteList(): List<NoteEntity> {
        TODO("Not yet implemented")
    }
}