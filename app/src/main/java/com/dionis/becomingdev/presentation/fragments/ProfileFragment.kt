package com.dionis.becomingdev.presentation.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dionis.becomingdev.databinding.FragmentProfileBinding
import com.dionis.becomingdev.presentation.adapter.UserAdapter
import com.dionis.becomingdev.util.helper.ImageHelper
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class ProfileFragment : Fragment() {



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


        endIconClick()
        editImage()


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