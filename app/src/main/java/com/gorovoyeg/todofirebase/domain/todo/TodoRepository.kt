package com.gorovoyeg.todofirebase.domain.todo

interface TodoRepository {

    suspend fun addNote(note: NoteEntity)

    suspend fun getNoteList(): List<NoteEntity>

    suspend fun deleteNote(note: NoteEntity)

    suspend fun getNote(id: String): NoteEntity

    suspend fun updateNote(note: NoteEntity): NoteEntity
}