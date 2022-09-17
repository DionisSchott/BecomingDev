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
import androidx.fragment.app.viewModels
import com.dionis.becomingdev.R
import com.dionis.becomingdev.base.States
import com.dionis.becomingdev.databinding.FragmentProfileBinding
import com.dionis.becomingdev.domain.model.MembersItem
import com.dionis.becomingdev.presentation.viewModels.PostPhotoViewModel
import com.dionis.becomingdev.presentation.viewModels.ProfileViewModel
import com.dionis.becomingdev.util.helper.ImageHelper
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class ProfileFragment : Fragment() {


    private val viewModelProfile by viewModels<ProfileViewModel>()
    private val viewModelPostPhoto by viewModels<PostPhotoViewModel>()

    private lateinit var photo: File
    private lateinit var binding: FragmentProfileBinding
    lateinit var imageHelper: ImageHelper


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageHelper = ImageHelper(requireActivity())
        photo = File("")
        viewModelProfile.getUserInfo(5)

        setUp()

    }

    private fun setUp() {
        setUpObservers()
        setUpClicks()
    }


    private fun setUpClicks() {
        binding.btnSend.setOnClickListener {

            editInfo()

        }
        endIconClick()
        editImage()
    }


    private fun setUpObservers() {
        viewModelProfile.getUserResult.observe(viewLifecycleOwner) {
            when (it) {
                is States.GetUserState.Success -> {
                    setDataUser(it.members)

                }
                is States.GetUserState.Loading -> {


                }
                is States.GetUserState.Failure -> {
                    Toast.makeText(requireContext(), "Erro ao carregar membro", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        viewModelPostPhoto.postPhoto.observe(viewLifecycleOwner) {
            when (it) {
                is States.PostPhotoState.Success -> {


                }
                is States.PostPhotoState.Loading -> {


                }
                is States.PostPhotoState.Error -> {
                    Toast.makeText(requireContext(), "Erro ao carregar membro", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        viewModelProfile.validateFields.observe(viewLifecycleOwner) {
            when (it) {
                is States.ValidateEditProfile.NameEmpty -> {
                    showObligatoryField(binding.edtNameLayout, R.string.obligatory_field)
                }
                is States.ValidateEditProfile.EmailEmpty -> {
                    showObligatoryField(binding.edtEmailLayout, R.string.obligatory_field)
                }
                is States.ValidateEditProfile.TechnologyEmpty -> {
                    showObligatoryField(binding.edtTechnologyLayout, R.string.obligatory_field)
                }
                is States.ValidateEditProfile.ExperinceEmpty -> {
                    showObligatoryField(binding.edtExperienceLayout, R.string.obligatory_field)
                }
                is States.ValidateEditProfile.SocialEmpty -> {
                    showObligatoryField(binding.edtSocialMediaLayout, R.string.obligatory_field)
                }

                is States.ValidateEditProfile.FieldsDone -> {
//                    editInfoValidate()


                }
            }

        }

    }


    private fun showObligatoryField(edt: TextInputLayout, message: Int) {
        edt.error = getString(message)
    }

    private fun setDataUser(membersItem: MembersItem) {

        if (!membersItem.Photos.isNullOrEmpty()) {

            val imageView = binding.imgProfile
            Picasso.get().load(membersItem.Photos[0].url).into(imageView)
        }

        binding.edtName.setText(membersItem.name)
        binding.edtEmail.setText(membersItem.email)
        binding.edtTechnology.setText(membersItem.technology)
        binding.edtExperience.setText(membersItem.experience)
        binding.edtSocialMedia.setText(membersItem.socials)

    }


//    private fun editInfoValidate() {
//
//            val name = binding.edtName.text.toString()
//            val email = binding.edtEmail.text.toString()
//            val technology = binding.edtTechnology.text.toString()
//            val experience = binding.edtExperience.text.toString()
//            val social = binding.edtSocialMedia.text.toString()
//
//
//            viewModelProfile.(
//                name,
//                email,
//                technology,
//                experience,
//                social,
//
//                )
//
//    }

    private fun editInfo() {

        val name = binding.edtName.text.toString()
        val email = binding.edtEmail.text.toString()
        val technology = binding.edtTechnology.text.toString()
        val experience = binding.edtExperience.text.toString()
        val social = binding.edtSocialMedia.text.toString()

        viewModelPostPhoto.postPhoto(photo, 5)

        viewModelProfile.validateFields(
            name,
            email,
            technology,
            experience,
            social,

            )

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
            imageHelper.handleResult(requestCode,
                resultCode,
                data,
                object : ImageHelper.Callback {
                    override fun onImageCompressed(
                        image64: String?,
                        imageBitmap: Bitmap?,
                        imageFile: File?,
                    ) {
                        binding.imgProfile.setImageBitmap(imageBitmap)
                        photo = imageFile!!

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

}
