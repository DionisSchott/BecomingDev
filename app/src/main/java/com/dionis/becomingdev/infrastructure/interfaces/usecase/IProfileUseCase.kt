package com.dionis.becomingdev.infrastructure.interfaces.usecase

import com.dionis.becomingdev.domain.model.Members
import com.dionis.becomingdev.domain.model.MembersItem
import com.dionis.becomingdev.domain.model.UserInfoResponse
import kotlinx.coroutines.flow.Flow

interface IProfileUseCase {

    suspend fun getUserInfo(memberId: Int): Flow<MembersItem>
}