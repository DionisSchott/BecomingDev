package com.dionis.auladokevyn.domain.registerMember


import com.dionis.auladokevyn.data.api.registermember.RegisterMemberApi
import com.dionis.auladokevyn.model.RegisterMemberBody
import com.dionis.auladokevyn.model.RegisterMemberResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterMemberRepositoryImpl @Inject constructor(private val registerMemberApi: RegisterMemberApi) : RegisterMemberRepository {

    override suspend fun newMember(body: RegisterMemberBody): Flow<RegisterMemberResponse> = flow{
        emit(registerMemberApi.newMember(body))

    }

}