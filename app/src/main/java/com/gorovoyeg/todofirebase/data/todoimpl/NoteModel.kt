package com.gorovoyeg.todofirebase.data.todoimpl

data class NoteModel(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val status: Int = 0,
    val date: String = ""
)