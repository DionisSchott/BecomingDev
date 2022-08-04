package com.dionis.auladokevyn.domain.registerMember

import com.dionis.auladokevyn.model.RegisterMemberBody
import com.dionis.auladokevyn.model.RegisterMemberResponse
import kotlinx.coroutines.flow.Flow

interface RegisterMemberRepository {
    suspend fun newMember(body: RegisterMemberBody): Flow<RegisterMemberResponse>
}