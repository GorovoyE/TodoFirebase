package com.gorovoyeg.todofirebase.presentation.edit

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gorovoyeg.todofirebase.R
import com.gorovoyeg.todofirebase.databinding.FragmentEditBinding
import com.gorovoyeg.todofirebase.domain.todo.NoteEntity
import com.gorovoyeg.todofirebase.presentation.main.MainListFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EditFragment : Fragment() {
    private lateinit var binding: FragmentEditBinding
    val viewModel: EditViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // TODO реализовать показ полученных параметров

        arguments?.let {
            val noteEntity = it.getString(KEY_EDIT_FRAGMENT)
//            binding.editTextTitle.text
//            binding.ediTextDescription.text
//            binding.textViewDate.text
//            binding.buttonStatus.text
        }
        binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onReadyNoteClicked()
    }

    private fun onReadyNoteClicked() {
        binding.buttonReady.setOnClickListener {
            val currentDate = System.currentTimeMillis()
            val date = DateFormat.format(DATE_FORMAT, currentDate).toString()
            // TODO реализовать логику изменения состояние заметки в зависимости от статуса
            viewModel.addNote(
                NoteEntity(
                    title = binding.editTextTitle.text.toString(),
                    description = binding.ediTextDescription.text.toString(),
                    status = 0,
                    date = date
                )
            )
            navigateToMainFragment()
        }
    }

    private fun navigateToMainFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_container, MainListFragment())
            .commit()
    }


    companion object {
        private const val KEY_EDIT_FRAGMENT = "key_edit_fragment"
        private const val DATE_FORMAT = "dd/MM/yyyy hh:mm"


        @JvmStatic
        fun newInstance(noteEntity: NoteEntity) =
            EditFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(KEY_EDIT_FRAGMENT, noteEntity)
                }
            }
    }
}