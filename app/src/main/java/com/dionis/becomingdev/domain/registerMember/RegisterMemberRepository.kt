package com.dionis.becomingdev.domain.registerMember

import com.dionis.becomingdev.model.RegisterMemberBody
import com.dionis.becomingdev.model.RegisterMemberResponse
import kotlinx.coroutines.flow.Flow

interface RegisterMemberRepository {
    suspend fun newMember(body: RegisterMemberBody): Flow<RegisterMemberResponse>
}