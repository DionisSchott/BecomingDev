package com.dionis.becomingdev.infrastructure.interfaces.repository

import com.dionis.becomingdev.domain.model.MembersItem
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    suspend fun getUserInfo(memberId: Int): Flow<MembersItem>
}