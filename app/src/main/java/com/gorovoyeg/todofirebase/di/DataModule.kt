package com.gorovoyeg.todofirebase.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.gorovoyeg.todofirebase.data.authimpl.AuthRepositoryImpl
import com.gorovoyeg.todofirebase.data.todoimpl.NoteMapper
import com.gorovoyeg.todofirebase.data.todoimpl.NoteModel
import com.gorovoyeg.todofirebase.data.todoimpl.TodoRepositoryImpl
import com.gorovoyeg.todofirebase.domain.auth.AuthRepository
import com.gorovoyeg.todofirebase.domain.todo.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        auth: FirebaseAuth,
        database: FirebaseFirestore
    ): AuthRepository {
        return AuthRepositoryImpl(auth, database)
    }

    @Provides
    @Singleton
    fun provideTodoRepository(
        auth: FirebaseAuth,
        database: FirebaseFirestore,
        mapper: NoteMapper,
        noteModel: NoteModel,
    ): TodoRepository {
        return TodoRepositoryImpl(auth, database, mapper, noteModel)
    }

    @Provides
    fun provideNoteModel(): NoteModel = NoteModel()
}