package com.dionis.becomingdev.domain.usecase

import com.dionis.becomingdev.domain.registerMember.RegisterMemberRepository
import com.dionis.becomingdev.model.registerMember.RegisterMemberBody
import com.dionis.becomingdev.model.registerMember.RegisterMemberResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterMemberUseCase @Inject constructor(private val registerMemberRepository: RegisterMemberRepository){

    suspend fun newMember(body: RegisterMemberBody): Flow<RegisterMemberResponse> {
        return registerMemberRepository.newMember(body)
    }
}