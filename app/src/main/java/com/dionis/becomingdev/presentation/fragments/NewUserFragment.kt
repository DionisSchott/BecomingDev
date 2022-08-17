package com.dionis.becomingdev.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dionis.becomingdev.databinding.FragmentLoginBinding
import com.dionis.becomingdev.databinding.FragmentNewUserBinding


class NewUserFragment : Fragment() {

    private lateinit var binding: FragmentNewUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNewUserBinding.inflate(inflater, container, false)
        return binding.root
    }

}