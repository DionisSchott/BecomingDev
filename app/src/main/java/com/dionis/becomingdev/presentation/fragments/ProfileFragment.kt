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
import com.dionis.becomingdev.data.storage.SessionManager
import com.dionis.becomingdev.databinding.FragmentProfileBinding
import com.dionis.becomingdev.domain.model.MembersItem
import com.dionis.becomingdev.presentation.viewModels.ProfileViewModel
import com.dionis.becomingdev.util.helper.ImageHelper
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class ProfileFragment : Fragment() {


    private val viewModel by viewModels<ProfileViewModel>()

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
        viewModel.getUserInfo(5)

        endIconClick()
        editImage()
        setUpObservers()

    }


    private fun setUpObservers() {
        viewModel.getUserResult.observe(viewLifecycleOwner) {
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
    }

private fun setDataUser(membersItem: MembersItem) {

    binding.edtName.setText(membersItem.name)
    binding.edtEmail.setText(membersItem.email)
    binding.edtTechnology.setText(membersItem.technology)
    binding.edtExperience.setText(membersItem.experience)
    binding.edtSocialMedia.setText(membersItem.socials)

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





}