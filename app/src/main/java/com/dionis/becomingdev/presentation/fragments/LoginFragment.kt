package com.dionis.becomingdev.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dionis.becomingdev.R
import com.dionis.becomingdev.base.States
import com.dionis.becomingdev.data.storage.UserManager
import com.dionis.becomingdev.databinding.FragmentLoginBinding
import com.dionis.becomingdev.presentation.activity.MainActivity
import com.dionis.becomingdev.presentation.viewModels.LoginViewModel
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel by viewModels<LoginViewModel>()

    private lateinit var binding: FragmentLoginBinding
    private lateinit var userManager: UserManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userManager = UserManager(requireContext())
        readDataUser()
        setUpClicks()
        setObservers()
        openRegisterScreen()
    }


    private fun setUpClicks() {
        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()

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
        viewModel.validateFields.observe(viewLifecycleOwner) {
            when (it) {
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
                    saveDataUserValidate()
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


    private fun saveDataUserValidate() {
        if (binding.checkBox.isChecked) {
            saveDataUser()
        } else lifecycleScope.launch { userManager.clearDataUser() }

    }

    private fun saveDataUser() {

        val name = binding.edtEmail.text.toString()
        val secondName = binding.edtPassword.text.toString()
        val authenticated = binding.checkBox.isChecked

        lifecycleScope.launch { userManager.saveDataUser(name, secondName, authenticated) }
    }

    private fun readDataUser() {
        lifecycleScope.launch {
            val user = userManager.readDataUser()

            binding.edtEmail.setText(user.name)
            binding.edtPassword.setText(user.secondName)
            binding.checkBox.isChecked = user.authenticated
        }
    }


}