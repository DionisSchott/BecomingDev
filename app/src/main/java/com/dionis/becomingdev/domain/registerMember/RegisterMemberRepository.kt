package com.dionis.becomingdev.domain.registerMember

import com.dionis.becomingdev.model.registerMember.RegisterMemberBody
import com.dionis.becomingdev.model.registerMember.RegisterMemberResponse
import kotlinx.coroutines.flow.Flow

interface RegisterMemberRepository {
    suspend fun newMember(body: RegisterMemberBody): Flow<RegisterMemberResponse>
}