package com.gorovoyeg.todofirebase.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.gorovoyeg.todofirebase.databinding.ListItemBinding
import com.gorovoyeg.todofirebase.domain.todo.NoteEntity

class TodoListAdapter : ListAdapter<NoteEntity, MainListViewHolder>(MainDiffUtil()) {

    var onItemClickListener: ((NoteEntity) -> Unit)? = null
    var onDeleteClickListener: ((NoteEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainListViewHolder {
        // TODO добавить выбор статуса в разметке
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainListViewHolder, position: Int) {
        val todoItem = getItem(position)
        // TODO и логику работы в зависимости от статуса
        with(holder.binding) {
            textViewStatus.text = todoItem.status.toString()
            textViewTitle.text = todoItem.title
            textViewDescription.text = todoItem.description
            textViewDate.text = todoItem.date
        }

        holder.binding.imageViewDelete.setOnClickListener {
            onDeleteClickListener?.invoke(todoItem)
        }

        holder.binding.root.setOnClickListener {
            onItemClickListener?.invoke(todoItem)
        }
    }
}