package com.dionis.auladokevyn.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dionis.auladokevyn.databinding.FragmentHomeBinding
import com.dionis.auladokevyn.presentation.adapter.MembersAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var membersAdapter: MembersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setup()
    }

    private fun setup(){
        setUpObservers()
        setupAdapters()
    }

    private fun setUpObservers(){

    }

    private fun setupAdapters() {
        membersAdapter = MembersAdapter()
        binding.rvMembers.adapter = membersAdapter


    }



}