package com.dionis.becomingdev.infrastructure.repository

import com.dionis.becomingdev.data.api.home.HomeApi
import com.dionis.becomingdev.domain.model.Members
import com.dionis.becomingdev.infrastructure.interfaces.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val homeApi: HomeApi): HomeRepository {

    override suspend fun getMembers(): Flow<Members> = flow{
        emit(homeApi.getMembers())

    }


}