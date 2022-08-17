package com.dionis.becomingdev.domain.registerMember


import com.dionis.becomingdev.data.api.registermember.RegisterMemberApi
import com.dionis.becomingdev.model.registerMember.RegisterMemberBody
import com.dionis.becomingdev.model.registerMember.RegisterMemberResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterMemberRepositoryImpl @Inject constructor(private val registerMemberApi: RegisterMemberApi) : RegisterMemberRepository {

    override suspend fun newMember(body: RegisterMemberBody): Flow<RegisterMemberResponse> = flow{
        emit(registerMemberApi.newMember(body))

    }

}