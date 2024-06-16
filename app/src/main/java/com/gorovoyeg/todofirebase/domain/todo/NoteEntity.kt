package com.gorovoyeg.todofirebase.domain.todo

import com.google.firebase.firestore.DocumentId
import java.io.Serializable
import javax.inject.Inject

data class NoteEntity @Inject constructor(
    @DocumentId val id: String = "",
    val title: String,
    val description: String,
    val status: Int,
    val date: String
): Serializable