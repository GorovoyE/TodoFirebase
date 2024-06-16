package com.gorovoyeg.todofirebase.domain.todo

import javax.inject.Inject

data class NoteEntity @Inject constructor(
    val id: String,
    val title: String,
    val description: String,
    val status: Int,
    val date: String
)