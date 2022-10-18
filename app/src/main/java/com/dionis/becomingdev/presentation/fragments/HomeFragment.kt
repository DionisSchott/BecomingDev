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
import androidx.navigation.fragment.findNavController
import com.dionis.becomingdev.R
import com.dionis.becomingdev.base.States
import com.dionis.becomingdev.databinding.FragmentHomeBinding
import com.dionis.becomingdev.presentation.adapter.MembersAdapter
import com.dionis.becomingdev.presentation.fragments.DetailsFragment.Companion.MEMBER
import com.dionis.becomingdev.presentation.viewModels.HomeViewModel
import com.dionis.becomingdev.util.helper.ImageHelper
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel>()

    private lateinit var binding: FragmentHomeBinding
    private lateinit var membersAdapter: MembersAdapter
    lateinit var imageHelper: ImageHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        imageHelper = ImageHelper(requireActivity())
        setup()
    }

    private fun setup() {

        viewModel.getMembers()
        setUpObservers()
        setupAdapters()
        setUpClicks()
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


                }

                override fun onCanceled() {
                    Log.e("EditProfileActivity", "Image Canceled")
                }

                override fun onError() {
                    //toast(R.string.image_error)
                }
            })
    }

    private fun setUpObservers() {
        viewModel.getMembersResult.observe(viewLifecycleOwner) {
            when (it) {
                is States.GetMembersState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    membersAdapter.updateList(it.members)

                }
                is States.GetMembersState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE

                }
                is States.GetMembersState.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Erro ao carregar membros", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun setUpClicks() {

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragmentHome_to_registerMemberFragment)
        }


    }

    private fun setupAdapters() {
        membersAdapter = MembersAdapter()

        membersAdapter.onItemClicked = {
            val args = Bundle().apply { putSerializable(MEMBER, it) }
            findNavController().navigate(R.id.action_homeFragmentHome_to_detailsFragment, args)
        }

        binding.rvMembers.adapter = membersAdapter
    }


}