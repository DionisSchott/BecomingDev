package com.dionis.becomingdev.infrastructure.interfaces.usecase

import com.dionis.becomingdev.domain.model.MembersItem
import com.dionis.becomingdev.model.editMember.EditUserBody
import kotlinx.coroutines.flow.Flow

interface IProfileUseCase {

    suspend fun editUser(memberId: Int, body: EditUserBody): Flow<MembersItem>
}