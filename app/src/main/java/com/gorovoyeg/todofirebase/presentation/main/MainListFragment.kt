package com.gorovoyeg.todofirebase.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gorovoyeg.todofirebase.R
import com.gorovoyeg.todofirebase.databinding.FragmentMainListBinding
import com.gorovoyeg.todofirebase.presentation.auth.AuthFragment
import com.gorovoyeg.todofirebase.presentation.edit.EditFragment
import com.gorovoyeg.todofirebase.presentation.main.adapter.TodoListAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainListFragment : Fragment() {

    private val adapter = TodoListAdapter()
    private lateinit var binding: FragmentMainListBinding
    val viewModel: MainListViewModel by viewModels()

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
        binding = FragmentMainListBinding.inflate(inflater, container, false)
        return binding.root
    }

    // TODO добавить показ количества задач со статусом "новая"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.recyclerViewMainList.adapter = adapter
        binding.recyclerViewMainList.layoutManager = LinearLayoutManager(requireContext())
        viewModel.todoList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        adapter.onItemClickListener = {
            EditFragment.newInstance(it)
            requireActivity().supportFragmentManager.popBackStack()
            requireActivity().supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragment_container, EditFragment())
                .commit()
        }

        adapter.onDeleteClickListener = {
            viewModel.deleteItem(it)
        }

        binding.buttonSignout.setOnClickListener {
            viewModel.signOut()
            navigateTo(AuthFragment())
        }

        binding.buttonAdd.setOnClickListener {
            navigateTo(EditFragment())
        }
    }

    private fun navigateTo(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_container, fragment)
            .commit()
    }


    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"


        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}