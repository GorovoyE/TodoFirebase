package com.gorovoyeg.todofirebase.data.todoimpl

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.gorovoyeg.todofirebase.domain.todo.NoteEntity
import com.gorovoyeg.todofirebase.domain.todo.TodoRepository
import com.gorovoyeg.todofirebase.util.CollectionFirestore
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    val auth: FirebaseAuth,
    val database: FirebaseFirestore,
    val mapper: NoteMapper,
    var noteModel: NoteModel
) : TodoRepository {
    var notesLIst = listOf<NoteModel>()

    override suspend fun addNote(note: NoteEntity) {
        database.collection(CollectionFirestore.NOTES)
            .document(auth.currentUser?.uid ?: "")
            .collection(CollectionFirestore.NOTES)
            .add(note)
            .addOnSuccessListener {
                Log.d("TodoRepositoryImpl", "Note added ${note}")
            }
            .addOnFailureListener {
                Log.d("TodoRepositoryImpl", "Note not added")
            }
    }

    override suspend fun getNoteList(): List<NoteEntity> {
        // TODO список приходит спустя некоторое время, стоит поставить заглушку или использовать стейт (предпочтительнее)
        // TODO возможно, стоит кешировать данные, например, в sharedpref
        database.collection(CollectionFirestore.NOTES)
            .document(auth.currentUser?.uid ?: "")
            .collection(CollectionFirestore.NOTES)
            .get()
            .addOnSuccessListener {
                val notes = it.toObjects(NoteModel::class.java)
                notesLIst = notes
                Log.d("TodoRepositoryImpl", "Notes get success ${notes}")
            }
            .addOnFailureListener {
                Log.d("TodoRepositoryImpl", "Notes get failure")
            }
        return mapper.mapNoteListModelToListEntity(notesLIst)
    }

    override suspend fun deleteNote(note: NoteEntity) {
        database.collection(CollectionFirestore.NOTES)
            .document(auth.currentUser?.uid ?: "")
            .collection(CollectionFirestore.NOTES)
            .document(note.id)
            .delete()
            .addOnCompleteListener {
                Log.d("TodoRepositoryImpl", "Note deleted")
            }
    }

    override suspend fun getNote(id: String): NoteEntity {
        database.collection(CollectionFirestore.NOTES)
            .document(auth.currentUser?.uid ?: "")
            .collection(CollectionFirestore.NOTES)
            .document(id)
            .get()
            .addOnSuccessListener {
                val note = it.toObject(NoteModel::class.java)
                if (note != null) {
                    noteModel = note
                }
                Log.d("TodoRepositoryImpl", "Note get success")
            }
            .addOnFailureListener {
                Log.d("TodoRepositoryImpl", "Note get failure")
            }
        return mapper.mapNoteModelToEntity(noteModel)
    }

    override suspend fun updateNote(note: NoteEntity): NoteEntity {
        database.collection(CollectionFirestore.NOTES)
            .document(auth.currentUser?.uid ?: "")
            .collection(CollectionFirestore.NOTES)
            .document(note.id)
            .set(note)
            .addOnCompleteListener {
                Log.d("TodoRepositoryImpl", "Note updated ${note}")
                noteModel = mapper.mapNoteEntityToModel(note)
            }
        return mapper.mapNoteModelToEntity(noteModel)
    }
}
