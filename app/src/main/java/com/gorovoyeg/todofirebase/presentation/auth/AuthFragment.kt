package com.gorovoyeg.todofirebase.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.gorovoyeg.todofirebase.R
import com.gorovoyeg.todofirebase.databinding.FragmentAuthBinding
import com.gorovoyeg.todofirebase.presentation.main.MainListFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AuthFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentAuthBinding
    val viewModel: AuthViewModel by viewModels()

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
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        enableButtons()
    }

    private fun enableButtons() {
        signInUser()
        signUpUser()
    }

    private fun signInUser() {
        // TODO стоит добавить информирование юзера о неуспешной попытке входа (сейчас все в логах)
        var userIsOnline = false
        binding.buttonSignIn.setOnClickListener {
            viewModel.getSignIn(
                login = binding.editTextLogin.text.toString() + "@gmail.com",
                password = binding.editTextPassword.text.toString()
            )
            viewLifecycleOwner.lifecycleScope.launch {
                userIsOnline = viewModel.checkCurrentUserIsOnline()
            }
            if (userIsOnline) {
                navigateToMainScreen()
            }

        }
    }

    private fun signUpUser() {
        // TODO стоит добавить информирование юзера о неуспешной попытке регистрации (сейчас все в логах)
        binding.buttonSignUp.setOnClickListener {
            viewModel.getSignUp(
                login = binding.editTextLogin.text.toString() + "@gmail.com",
                password = binding.editTextPassword.text.toString()
            )
        }
    }


    private fun navigateToMainScreen() {
        requireActivity().supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_container, MainListFragment())
            .commit()
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