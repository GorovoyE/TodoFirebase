package com.gorovoyeg.todofirebase.data.todoimpl

import com.google.firebase.firestore.DocumentId

data class NoteModel(
    @DocumentId val id: String = "",
    val title: String = "",
    val description: String = "",
    val status: Int = 0,
    val date: String = ""
)