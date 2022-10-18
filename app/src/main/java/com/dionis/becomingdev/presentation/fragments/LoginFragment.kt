package com.dionis.becomingdev.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dionis.becomingdev.R
import com.dionis.becomingdev.base.States
import com.dionis.becomingdev.databinding.FragmentLoginBinding
import com.dionis.becomingdev.presentation.activity.MainActivity
import com.dionis.becomingdev.presentation.viewModels.LoginViewModel
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel by viewModels<LoginViewModel>()

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpClicks()
        setObservers()
        openRegisterScreen()
        autoLogin()
    }


    private fun autoLogin() {
        if (!binding.edtEmail.text.isNullOrEmpty() && !binding.edtPassword.text.isNullOrEmpty()){
            val email = binding.edtEmail.toString()
            val password = binding.edtPassword.toString()

            viewModel.validateFields(
                email,
                password
            )
            setObservers()
        }
    }

    private fun setUpClicks() {
        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.toString()
            val password = binding.edtPassword.toString()

            viewModel.validateFields(
                email,
                password
            )
        }

    }

    private fun openRegisterScreen() {
        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_newUserFragment)
        }
    }

    private fun openHomeScreen() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    private fun setObservers() {
        viewModel.validateFields.observe(
            viewLifecycleOwner
        ) { state ->
            when (state) {
                is States.ValidateLogin.EmailEmpty -> {
                    showObligatoryField(binding.edtEmailLayout, R.string.obligatory_field)
                }
                is States.ValidateLogin.PasswordEmpty -> {
                    showObligatoryField(binding.edtPassLayout, R.string.obligatory_field)
                }

                is States.ValidateLogin.FieldsDone -> {
                    login()
                }
            }

        }
        viewModel.loginDone.observe(viewLifecycleOwner) {
            when (it) {
                is States.LoginState.Success -> {
                    openHomeScreen()
                }
                is States.LoginState.Loading -> {

                }
                is States.LoginState.Error -> {

                }
            }
        }

    }


    private fun showObligatoryField(edt: TextInputLayout, message: Int) {
        edt.error = getString(message)
    }


    private fun login() {
        val email = binding.edtEmail.text.toString()
        val password = binding.edtPassword.text.toString()

        viewModel.login(email, password)
    }


}