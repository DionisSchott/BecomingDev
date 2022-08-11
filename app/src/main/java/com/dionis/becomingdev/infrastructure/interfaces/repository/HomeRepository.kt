package com.dionis.becomingdev.infrastructure.interfaces.repository

import com.dionis.becomingdev.domain.model.Members
import kotlinx.coroutines.flow.Flow


interface HomeRepository  {

    suspend fun getMembers(): Flow<Members>


}