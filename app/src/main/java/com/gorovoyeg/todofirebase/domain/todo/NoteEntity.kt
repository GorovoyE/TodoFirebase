package com.gorovoyeg.todofirebase.domain.todo

import java.util.Date

data class NoteEntity(
    val id: Int = UNDEFINED_ID,
    val title: String,
    val description: String,
    val status: Status,
    val data: Date
) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}

enum class Status {
    NEW, INWORK, DONE
}
