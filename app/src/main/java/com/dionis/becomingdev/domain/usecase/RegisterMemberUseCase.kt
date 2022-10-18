package com.dionis.becomingdev.domain.usecase

import com.dionis.becomingdev.domain.model.MembersItem
import com.dionis.becomingdev.infrastructure.interfaces.repository.RegisterMemberRepository
import com.dionis.becomingdev.model.registerMember.RegisterMemberBody
import com.dionis.becomingdev.model.registerMember.RegisterMemberResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterMemberUseCase @Inject constructor(private val registerMemberRepository: RegisterMemberRepository){

    suspend fun registerMember(body: RegisterMemberBody): Flow<RegisterMemberResponse> {
        return registerMemberRepository.newMember(body)
    }

    suspend fun deleteMember(id: Int): Flow<MembersItem> {
        return registerMemberRepository.deleteMember(id)
    }
}