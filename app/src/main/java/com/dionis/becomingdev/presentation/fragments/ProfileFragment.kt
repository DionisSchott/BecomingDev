package com.dionis.becomingdev.presentation.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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


    private val viewModel by viewModels<ProfileViewModel>()

    private lateinit var binding: FragmentProfileBinding
    lateinit var imageHelper: ImageHelper
    lateinit var memberEdit: MembersItem
    private var jobId: Int = 0


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

        endIconClick()
        editImage()
        setDataUser()


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

    }

    private fun callRequest() {

        jobId = memberEdit.id
        val name = binding.edtName.text.toString()
        val lastName = binding.edtLastname.text.toString()
        val age = binding.edtAge.text.toString().toInt()
        val technology =  binding.edtTechnology.text.toString()
        val experience = binding.edtExperience.text.toString()
        val socials = binding.edtSocialMedia.text.toString()
        val email = binding.edtEmail.text.toString()
        val contact = binding.edtContact.unMasked

        viewModel.editUser(jobId, name, lastName, age, technology, experience, socials, email, contact)

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