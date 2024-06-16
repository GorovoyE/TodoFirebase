package com.gorovoyeg.todofirebase.presentation.main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.gorovoyeg.todofirebase.domain.todo.NoteEntity

class MainDiffUtil : DiffUtil.ItemCallback<NoteEntity>() {
    override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
        return oldItem.id == newItem.id
    }
}