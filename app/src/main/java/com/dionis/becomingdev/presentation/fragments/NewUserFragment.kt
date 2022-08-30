package com.dionis.becomingdev.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dionis.becomingdev.R
import com.dionis.becomingdev.base.States
import com.dionis.becomingdev.databinding.FragmentNewUserBinding
import com.dionis.becomingdev.presentation.viewModels.NewUserViewModel
import com.dionis.becomingdev.presentation.viewModels.RegisterMemberViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewUserFragment : Fragment() {

    private val viewModel by viewModels<NewUserViewModel>()

    private lateinit var binding: FragmentNewUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNewUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpClicks()
        setObservers()
        backPage()
    }

    private fun backPage() {
        binding.btnCancel.setOnClickListener {findNavController().popBackStack()}
    }

    private fun setUpClicks() {
        binding.btnDone.setOnClickListener {
            val name = binding.edtUserName.text.toString()
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()


            viewModel.validateFields(
                name,
                email,
                password
            )
        }
    }

    private fun setObservers() {
        viewModel.validateFields.observe(
            viewLifecycleOwner
        ) { state ->
            when (state) {
                is States.ValidateAddNewUser.UserNameEmpty -> {
                showObligatoryField(binding.edtUserName, R.string.obligatory_field)
                }
                is States.ValidateAddNewUser.EmailEmpty -> {
                    showObligatoryField(binding.edtEmail, R.string.obligatory_field)
                }
                is States.ValidateAddNewUser.PasswordEmpty -> {
                    showObligatoryField(binding.edtPassword, R.string.obligatory_field)
                }

                is States.ValidateAddNewUser.FieldsDone -> {
                    registerNewUser()
                }

            }

        }
        viewModel.addNewUser.observe(viewLifecycleOwner) {
            when (it) {
                is States.AddNewUserState.Success -> {
                    findNavController().popBackStack()
                }
                is States.AddNewUserState.Loading -> {

                }
                is States.AddNewUserState.Error -> {

                }
            }

        }
    }

    private fun showObligatoryField(edt: EditText, message: Int) {
        edt.error = getString(message)
    }

    private fun registerNewUser() {
        val username = binding.edtUserName.text.toString()
        val email = binding.edtEmail.text.toString()
        val password = binding.edtPassword.text.toString()

        viewModel.newMember(username,email,password)
    }


}