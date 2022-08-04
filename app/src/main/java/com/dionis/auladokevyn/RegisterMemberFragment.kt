package com.dionis.auladokevyn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dionis.auladokevyn.databinding.FragmentRegisterMemberBinding


class RegisterMemberFragment : Fragment() {

    private lateinit var binding: FragmentRegisterMemberBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRegisterMemberBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun registerMember() {
        val name = binding.edtName.text.toString()
        val lastname = binding.edtLastName.toString()
        val age = binding.edtAge.text.toString()
    }

    // TODO: PAREI AQUI


}