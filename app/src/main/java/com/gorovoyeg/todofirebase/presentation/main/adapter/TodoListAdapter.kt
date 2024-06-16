package com.gorovoyeg.todofirebase.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.gorovoyeg.todofirebase.databinding.ListItemBinding
import com.gorovoyeg.todofirebase.domain.todo.NoteEntity

class TodoListAdapter : ListAdapter<NoteEntity, MainListViewHolder>(MainDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainListViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainListViewHolder, position: Int) {
        val todoItem = getItem(position)
        holder.binding.textViewStatus.text = todoItem.status.toString()
        holder.binding.textViewTitle.text = todoItem.title
        holder.binding.textViewDescription.text = todoItem.description
        holder.binding.textViewDate.text = todoItem.date
    }
}