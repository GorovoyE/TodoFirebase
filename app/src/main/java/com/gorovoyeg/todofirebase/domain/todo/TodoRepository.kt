package com.gorovoyeg.todofirebase.domain.todo

interface TodoRepository {

    suspend fun addNote(note: NoteEntity)

    suspend fun getNoteList(): List<NoteEntity>
}