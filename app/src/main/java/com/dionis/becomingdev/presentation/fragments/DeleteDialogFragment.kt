package com.dionis.becomingdev.presentation.fragments

import android.location.SettingInjectorService
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.dionis.becomingdev.R
import com.dionis.becomingdev.databinding.FragmentCameraOrGaleryDialogBinding
import com.dionis.becomingdev.databinding.FragmentDeleteDialogBinding


class DeleteDialogFragment(private var deleteUser: () -> Unit, private var back: () -> Unit) : DialogFragment() {

    private lateinit var binding: FragmentDeleteDialogBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDeleteDialogBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpClicks()
    }

    override fun getTheme() = R.style.RoundedCornersDialog

    private fun setUpClicks(){

        binding.idDelete.setOnClickListener {deleteUser.invoke()}
        binding.idCancel.setOnClickListener {dismiss()}
    }


}