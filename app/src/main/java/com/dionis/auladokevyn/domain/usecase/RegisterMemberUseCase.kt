package com.dionis.auladokevyn.domain.usecase

import com.dionis.auladokevyn.domain.model.Members
import com.dionis.auladokevyn.domain.registerMember.RegisterMemberRepository
import com.dionis.auladokevyn.model.RegisterMemberBody
import com.dionis.auladokevyn.model.RegisterMemberResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterMemberUseCase @Inject constructor(private val registerMemberRepository: RegisterMemberRepository){

    suspend fun newMember(body: RegisterMemberBody): Flow<RegisterMemberResponse> {
        return registerMemberRepository.newMember(body)
    }
}