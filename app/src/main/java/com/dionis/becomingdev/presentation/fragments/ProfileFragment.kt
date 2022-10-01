package com.dionis.becomingdev.presentation.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dionis.becomingdev.R
import com.dionis.becomingdev.base.States
import com.dionis.becomingdev.databinding.FragmentProfileBinding
import com.dionis.becomingdev.domain.model.MembersItem
import com.dionis.becomingdev.presentation.viewModels.ProfileViewModel
import com.dionis.becomingdev.util.helper.ImageHelper
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class ProfileFragment : Fragment() {


    private val viewModel: ProfileViewModel by activityViewModels()

    private lateinit var binding: FragmentProfileBinding
    lateinit var imageHelper: ImageHelper
    lateinit var memberEdit: MembersItem
    private var memberId: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        memberEdit = arguments?.getSerializable(MEMBER_EDIT) as MembersItem
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageHelper = ImageHelper(requireActivity())
        memberId = memberEdit.id

        setUpClicks()
        endIconClick()
        editImage()
        setDataUser()
        setObservers()


    }


//    private fun setUpObservers() {
//        viewModel.getUserResult.observe(viewLifecycleOwner) {
//            when (it) {
//                is States.GetUserState.Success -> {
//                    setDataUser(it.members)
//
//                }
//                is States.GetUserState.Loading -> {
//
//
//                }
//                is States.GetUserState.Failure -> {
//                   Toast.makeText(requireContext(), "Erro ao carregar membro", Toast.LENGTH_SHORT)
//                        .show()
//                }
//            }
//        }
//    }


    private fun setDataUser() {

        if (memberEdit.Photos.isNotEmpty()) {
            Picasso.get().load(memberEdit.Photos[0].url).into(binding.imgProfile)
        }
        binding.edtName.setText(memberEdit.name)
        binding.edtLastname.setText(memberEdit.lastname)
        binding.edtAge.setText(memberEdit.age.toString())
        binding.edtEmail.setText(memberEdit.email)
        binding.edtTechnology.setText(memberEdit.technology)
        binding.edtExperience.setText(memberEdit.experience)
        binding.edtSocialMedia.setText(memberEdit.socials)
        binding.edtContact.setText(memberEdit.contact)

    }

    private fun setUpClicks() {
        binding.btnSave.setOnClickListener{
            val name = binding.edtName.text.toString()
            val lastName = binding.edtLastname.text.toString()
            val age = binding.edtAge.text.toString().toInt()
            val technology =  binding.edtTechnology.text.toString()
            val experience = binding.edtExperience.text.toString()
            val socials = binding.edtSocialMedia.text.toString()
            val email = binding.edtEmail.text.toString()
            val contact = binding.edtContact.unMasked

            viewModel.validateFields(name, lastName, age, technology, experience, socials, email, contact)
        }
    }

    private fun editMember() {

        val memberId = memberId
        val name = binding.edtName.text.toString()
        val lastName = binding.edtLastname.text.toString()
        val age = binding.edtAge.text.toString().toInt()
        val technology =  binding.edtTechnology.text.toString()
        val experience = binding.edtExperience.text.toString()
        val socials = binding.edtSocialMedia.text.toString()
        val email = binding.edtEmail.text.toString()
        val contact = binding.edtContact.unMasked

        viewModel.editUser(memberId, name, lastName, age, technology, experience, socials, email, contact)

    }

    private fun setObservers() {
        viewModel.validateFields.observe(
            viewLifecycleOwner
        ) { state ->
            when (state) {
                is States.ValidateEditMember.NameEmpty -> {
                    showObligatoryField(binding.edtName, R.string.obligatory_field)
                }
                is States.ValidateEditMember.LastnameEmpty -> {
                    showObligatoryField(binding.edtLastname, R.string.obligatory_field)
                }
                is States.ValidateEditMember.AgeEmpty -> {
                    showObligatoryField(binding.edtAge, R.string.obligatory_field)
                }
                is States.ValidateEditMember.TechnologyEmpty -> {
                    showObligatoryField(binding.edtTechnology, R.string.obligatory_field)
                }
                is States.ValidateEditMember.ExperienceEmpty -> {
                    showObligatoryField(binding.edtExperience, R.string.obligatory_field)
                }
                is States.ValidateEditMember.SocielEmpty -> {
                    showObligatoryField(binding.edtSocialMedia, R.string.obligatory_field)
                }
                is States.ValidateEditMember.EmailEmpty -> {
                    showObligatoryField(binding.edtEmail, R.string.obligatory_field)
                }
                is States.ValidateEditMember.ContactEmpty -> {
                    showObligatoryField(binding.edtContact, R.string.obligatory_field)
                }

                is States.ValidateEditMember.FieldsDone -> {
                    editMember()
                }

            }
        }

        viewModel.editUser.observe(
            viewLifecycleOwner
        ) {
            when (it) {
                is States.EditMemberState.Success -> {
                    onEditSuccess(it.members)
                }
                is States.EditMemberState.Loading -> {

                }
                is States.EditMemberState.Failure -> {

                }
            }
        }

    }

    private fun onEditSuccess(newUserInfo: MembersItem) {

        viewModel.setNewUserInfo(newUserInfo)
        findNavController().popBackStack()
        Toast.makeText(context, "Atualizado com suceso", Toast.LENGTH_SHORT).show()
    }

    private fun showObligatoryField(edt: EditText, message: Int) {
        edt.error = getString(message)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        imageHelper.onRequestPermissionsResult(requestCode, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ImageHelper.GALLERY_REQUEST_CODE || requestCode == ImageHelper.PHOTO_REQUEST_CODE)
            imageHelper.handleResult(requestCode, resultCode, data, object : ImageHelper.Callback {
                override fun onImageCompressed(
                    image64: String?,
                    imageBitmap: Bitmap?,
                    imageFile: File?,
                ) {
                    binding.imgProfile.setImageBitmap(imageBitmap)

                }

                override fun onCanceled() {
                    Log.e("EditProfileActivity", "Image Canceled")
                }

                override fun onError() {
                    //toast(R.string.image_error)
                }
            })
    }


    private fun editImage() {
        binding.imgProfile.setOnClickListener {
            imageHelper.alertGenericTwoButtons(childFragmentManager)
        }
    }


    private fun endIconClick() {
        binding.edtNameLayout.setEndIconOnClickListener { binding.edtName.text?.clear() }
        binding.edtEmailLayout.setEndIconOnClickListener { binding.edtEmail.text?.clear() }
        binding.edtTechnologyLayout.setEndIconOnClickListener { binding.edtTechnology.text?.clear() }
        binding.edtExperienceLayout.setEndIconOnClickListener { binding.edtExperience.text?.clear() }
        binding.edtSocialMediaLayout.setEndIconOnClickListener { binding.edtSocialMedia.text?.clear() }

    }

    companion object {
        const val MEMBER_EDIT = "member edit"
    }


}