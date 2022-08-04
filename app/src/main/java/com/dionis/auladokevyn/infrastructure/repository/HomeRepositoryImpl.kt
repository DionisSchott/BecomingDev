package com.dionis.auladokevyn.infrastructure.repository

import com.dionis.auladokevyn.data.api.home.HomeApi
import com.dionis.auladokevyn.domain.model.Members
import com.dionis.auladokevyn.infrastructure.interfaces.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val homeApi: HomeApi): HomeRepository {

    override suspend fun getMembers(): Flow<Members> = flow{
        emit(homeApi.getMembers())

    }


}