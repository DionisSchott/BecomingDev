package com.dionis.becomingdev.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.dionis.becomingdev.R
import com.dionis.becomingdev.databinding.FragmentCameraOrGaleryDialogBinding

class CameraOrGaleryDialogFragment(private var openCamera: () -> Unit, private var openGallery: () -> Unit) : DialogFragment() {


    private lateinit var binding: FragmentCameraOrGaleryDialogBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCameraOrGaleryDialogBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpClicks()
    }

    override fun getTheme() = R.style.RoundedCornersDialog

    private fun setUpClicks(){

        binding.idCamera.setOnClickListener {openCamera.invoke()}
        binding.idGallery.setOnClickListener {openGallery.invoke()}
    }



}