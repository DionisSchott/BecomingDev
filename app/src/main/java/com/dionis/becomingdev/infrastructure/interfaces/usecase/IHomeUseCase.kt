package com.dionis.becomingdev.infrastructure.interfaces.usecase

import com.dionis.becomingdev.domain.model.Members
import kotlinx.coroutines.flow.Flow

interface IHomeUseCase {

    suspend fun getMembers(): Flow<Members>
}