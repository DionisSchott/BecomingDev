package com.dionis.auladokevyn.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dionis.auladokevyn.base.States
import com.dionis.auladokevyn.databinding.FragmentHomeBinding
import com.dionis.auladokevyn.presentation.adapter.MembersAdapter
import com.dionis.auladokevyn.presentation.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel>()

    private lateinit var binding: FragmentHomeBinding
    private lateinit var membersAdapter: MembersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setup()
    }

    private fun setup() {
        viewModel.getMembers()
        setUpObservers()
        setupAdapters()
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

    private fun setupAdapters() {
        membersAdapter = MembersAdapter()
        binding.rvMembers.adapter = membersAdapter
    }


}