package com.dionis.becomingdev.infrastructure.interfaces.repository

import com.dionis.becomingdev.domain.model.MembersItem
import com.dionis.becomingdev.model.editMember.EditUserBody
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    suspend fun editUser(memberId: Int, body: EditUserBody): Flow<MembersItem>
}