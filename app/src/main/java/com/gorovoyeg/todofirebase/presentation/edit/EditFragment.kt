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

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        private const val DATE_FORMAT = "dd/MM/yyyy hh:mm"


        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EditFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}