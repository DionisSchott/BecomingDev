package com.dionis.becomingdev.presentation.adapter

import com.dionis.becomingdev.databinding.FragmentProfileBinding
import com.dionis.becomingdev.domain.model.MembersItem

class UserAdapter {

    private lateinit var binding: FragmentProfileBinding
    lateinit var information: MembersItem

    fun bind(membersInfo: MembersItem) {


        this.information = membersInfo

//            Picasso.get().load(membersInfo.Photos[0].url).into(binding.imgMembers)
        binding.edtName.setText(membersInfo.name)
        binding.edtTechnology.setText(membersInfo.technology)

        binding.edtEmail.setText(membersInfo.email)


    }
}
