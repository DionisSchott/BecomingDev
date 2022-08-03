package com.dionis.auladokevyn.infrastructure.interfaces.usecase

import com.dionis.auladokevyn.domain.model.Members
import kotlinx.coroutines.flow.Flow

interface IHomeUseCase {

    suspend fun getMembers(): Flow<Members>
}