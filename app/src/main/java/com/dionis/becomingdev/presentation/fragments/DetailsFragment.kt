package com.dionis.becomingdev.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.dionis.becomingdev.R
import com.dionis.becomingdev.databinding.FragmentDetailsBinding
import com.dionis.becomingdev.databinding.FragmentProfileBinding
import com.dionis.becomingdev.domain.model.MembersItem
import com.dionis.becomingdev.presentation.viewModels.HomeViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {


    private lateinit var binding: FragmentDetailsBinding
    private lateinit var memberDetail: MembersItem


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        memberDetail = arguments?.getSerializable(MEMBER) as MembersItem
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpScreen()

    }

    private fun setUpScreen() {
        if (memberDetail.Photos.isEmpty())
        else{Picasso.get().load(memberDetail.Photos[0].url).into(binding.imageView)}
        binding.tvUserName.text = memberDetail.name + " " + memberDetail.lastname
        binding.tvUserAge.text = memberDetail.age.toString()
        binding.tvLanguages.text = memberDetail.technology
        binding.tvExperience.text = memberDetail.experience
        binding.tvEmail.text = memberDetail.email
        binding.tvSocial.text = memberDetail.socials
        binding.tvContact.text = memberDetail.contact
    }

    companion object {
        const val MEMBER = "member"
    }

}