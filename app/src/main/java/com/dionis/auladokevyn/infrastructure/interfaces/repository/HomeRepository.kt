package com.dionis.auladokevyn.infrastructure.interfaces.repository

import com.dionis.auladokevyn.domain.model.Members
import kotlinx.coroutines.flow.Flow


interface HomeRepository  {

    suspend fun getMembers(): Flow<Members>


}