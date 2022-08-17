package com.dionis.becomingdev.presentation.fragments

import android.app.ActionBar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dionis.becomingdev.R
import com.dionis.becomingdev.databinding.FragmentRegisterMemberBinding
import com.dionis.becomingdev.base.States
import com.dionis.becomingdev.presentation.viewModels.registerMember.RegisterMemberViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterMemberFragment : Fragment() {

    private val viewModel by viewModels<RegisterMemberViewModel>()

    private lateinit var binding: FragmentRegisterMemberBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentRegisterMemberBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpClicks()
        setObservers()
        backPage()
    }


    private fun setUpClicks() {
        binding.btnSend.setOnClickListener {
            val name = binding.edtName.text.toString()
            val lastname = binding.edtLastName.text.toString()
            val age = binding.edtAge.text.toString()
            val technology = binding.edtTechnology.text.toString()
            val experience = binding.edtExperience.text.toString()
            val social = binding.edtSocial.text.toString()
            val email = binding.edtEmail.text.toString()
            val contact = binding.edtContact.unMasked

            viewModel.validateFields(
                name,
                lastname,
                age,
                technology,
                experience,
                social,
                email,
                contact
            )
        }
    }

    private fun backPage() {
        binding.btnBack.setOnClickListener {findNavController().popBackStack()}
    }

    private fun setObservers() {
        viewModel.validateFields.observe(
            viewLifecycleOwner
        ) { state ->
            when (state) {
                is States.ValidateAddNewMember.NameEmpty -> {
                    showObligatoryField(binding.edtName, R.string.obligatory_field)
                }
                is States.ValidateAddNewMember.LastnameEmpty -> {
                    showObligatoryField(binding.edtLastName, R.string.obligatory_field)
                }
                is States.ValidateAddNewMember.AgeEmpty -> {
                    showObligatoryField(binding.edtAge, R.string.obligatory_field)
                }
                is States.ValidateAddNewMember.TechnologyEmpty -> {
                    showObligatoryField(binding.edtTechnology, R.string.obligatory_field)
                }
                is States.ValidateAddNewMember.ExperienceEmpty -> {
                    showObligatoryField(binding.edtExperience, R.string.obligatory_field)
                }
                is States.ValidateAddNewMember.SocielEmpty -> {
                    showObligatoryField(binding.edtSocial, R.string.obligatory_field)
                }
                is States.ValidateAddNewMember.EmailEmpty -> {
                    showObligatoryField(binding.edtEmail, R.string.obligatory_field)
                }
                is States.ValidateAddNewMember.ContactEmpty -> {
                    showObligatoryField(binding.edtContact, R.string.obligatory_field)
                }

                is States.ValidateAddNewMember.FieldsDone -> {
                    registerMember()
                }

            }
        }

    }

    private fun showObligatoryField(edt: EditText, message: Int) {
        edt.error = getString(message)
    }


    private fun registerMember() {
        val name = binding.edtName.text.toString()
        val lastname = binding.edtLastName.text.toString()
        val age = binding.edtAge.text.toString().toInt()
        val technology = binding.edtTechnology.text.toString()
        val experience = binding.edtExperience.text.toString()
        val social = binding.edtSocial.text.toString()
        val email = binding.edtEmail.text.toString()
        val contact = binding.edtContact.text.toString()


        viewModel.registerMember(
            name, lastname, age, technology, experience, social, email, contact)
    }

}