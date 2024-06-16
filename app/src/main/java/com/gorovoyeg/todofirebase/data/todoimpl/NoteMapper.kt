package com.gorovoyeg.todofirebase.data.todoimpl

import com.gorovoyeg.todofirebase.domain.todo.NoteEntity
import javax.inject.Inject

class NoteMapper @Inject constructor() {

    fun mapNoteEntityToModel(noteEntity: NoteEntity): NoteModel {
        return NoteModel(
            id = noteEntity.id,
            title = noteEntity.title,
            description = noteEntity.description,
            status = noteEntity.status,
            date = noteEntity.date
        )
    }

    fun mapNoteModelToEntity(noteModel: NoteModel): NoteEntity {
        return NoteEntity(
            id = noteModel.id,
            title = noteModel.title,
            description = noteModel.description,
            status = noteModel.status,
            date = noteModel.date
        )
    }

    fun mapNoteListModelToListEntity(noteListModel: List<NoteModel>) =
        noteListModel.map { mapNoteModelToEntity(it) }.toList()
}