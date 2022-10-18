package com.dionis.becomingdev.infrastructure.interfaces.repository

import com.dionis.becomingdev.domain.model.MembersItem
import com.dionis.becomingdev.model.registerMember.RegisterMemberBody
import com.dionis.becomingdev.model.registerMember.RegisterMemberResponse
import kotlinx.coroutines.flow.Flow

interface RegisterMemberRepository {
    suspend fun newMember(body: RegisterMemberBody): Flow<RegisterMemberResponse>

    suspend fun deleteMember(id: Int): Flow<MembersItem>
}