package com.gorovoyeg.todofirebase.presentation.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.gorovoyeg.todofirebase.databinding.FragmentAuthBinding
import com.gorovoyeg.todofirebase.presentation.MainListFragment

class AuthFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentAuthBinding
    private lateinit var viewModel: AuthViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]

        viewModel.isUserSignIn.observe(requireActivity()){
            if (it) navigateToMainScreen()
        }

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.buttonSignIn.setOnClickListener {
            viewModel.getSignIn(
                login = binding.editTextLogin.text.toString() + "@gmail.com",
                password = binding.editTextPassword.text.toString()
            )
            checkUserOnline()
        }

        binding.buttonSignUp.setOnClickListener {
            viewModel.getSignUp(
                login = binding.editTextLogin.text.toString() + "@gmail.com",
                password = binding.editTextPassword.text.toString()
            )
        }

        binding.buttonSignOut.setOnClickListener {
            viewModel.signOut()
        }
    }

    private fun checkUserOnline() {
        viewModel.isUserSignIn.observe(requireActivity()) {
            if (it) navigateToMainScreen()
        }
    }

    private fun navigateToMainScreen() {
        MainListFragment.newInstance("", "")
    }




    companion object {

        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AuthFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}