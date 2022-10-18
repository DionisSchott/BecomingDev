package com.dionis.becomingdev.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dionis.becomingdev.R
import com.dionis.becomingdev.base.States
import com.dionis.becomingdev.databinding.FragmentDetailsBinding
import com.dionis.becomingdev.domain.model.MembersItem
import com.dionis.becomingdev.presentation.fragments.ProfileFragment.Companion.MEMBER_EDIT
import com.dionis.becomingdev.presentation.viewModels.ProfileViewModel
import com.dionis.becomingdev.presentation.viewModels.RegisterMemberViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val viewModel: ProfileViewModel by activityViewModels()


    private val registerMemberViewModel by viewModels<RegisterMemberViewModel>()
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var memberDetail: MembersItem


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        // recebe info de usuário do adapter
        memberDetail = arguments?.getSerializable(MEMBER) as MembersItem
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpScreen()
        setUpClicks()
        setUpObserver()

    }


    private fun setUpClicks() {
        deleteMember(childFragmentManager)
        editInfoScreen()
        backPage()
    }

    private fun backPage() {
        binding.btnBack.setOnClickListener{findNavController().popBackStack()}
    }

    private fun setUpObserver() {
        viewModel.newUserInfo.observe(viewLifecycleOwner) {
            setUpUser(it)
        }

        registerMemberViewModel.deleteMember.observe(
            viewLifecycleOwner
        ) {
            when (it) {
                is States.DeleteMemberState.Success -> {
                    Toast.makeText(context, "MEMBRO DELETADO", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }
                is States.DeleteMemberState.Loading -> {

                }
                is States.DeleteMemberState.Error -> {

                }
            }
        }
    }

    // recebe informações editadas
    private fun setUpUser(membersItem: MembersItem) {
        binding.tvUserName.text = membersItem.name + " " + membersItem.lastname
        binding.tvUserAge.text = membersItem.age.toString()
        binding.tvLanguages.text = membersItem.technology
        binding.tvExperience.text = membersItem.experience
        binding.tvEmail.text = membersItem.email
        binding.tvSocial.text = membersItem.socials
        binding.tvContact.setText(membersItem.contact)

    }

    // seta info do usuário do adapter
    private fun setUpScreen() {
        if (memberDetail.Photos.isNotEmpty()) {
            Picasso.get().load(memberDetail.Photos[0].url).into(binding.imageView)
        }
        binding.tvUserName.text = memberDetail.name + " " + memberDetail.lastname
        binding.tvUserAge.text = memberDetail.age.toString()
        binding.tvLanguages.text = memberDetail.technology
        binding.tvExperience.text = memberDetail.experience
        binding.tvEmail.text = memberDetail.email
        binding.tvSocial.text = memberDetail.socials
        binding.tvContact.setText(memberDetail.contact)
    }


    // manda info de usuário para tela de edição
    private fun editInfoScreen() {
        binding.btnEdit.setOnClickListener {
            val args = Bundle().apply { putSerializable(MEMBER_EDIT, memberDetail) }
            findNavController().navigate(R.id.action_detailsFragment_to_profileFragment, args)
        }
    }

    private fun deleteMember(fragmentManager: androidx.fragment.app.FragmentManager) {
        binding.btnDelete.setOnClickListener {
            DeleteDialogFragment(deleteUser = { deleteMemberSelected() },
                back = { } ).show(fragmentManager, "")
        }
    }


    private fun deleteMemberSelected() {
        val id = memberDetail.id
        registerMemberViewModel.deleteMember(id)
        findNavController().popBackStack()
    }


    companion object {
        const val MEMBER = "member"
    }

}