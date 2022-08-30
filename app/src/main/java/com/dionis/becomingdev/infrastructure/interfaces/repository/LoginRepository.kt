package com.dionis.becomingdev.infrastructure.interfaces.repository

import com.dionis.becomingdev.model.login.UserBody
import com.dionis.becomingdev.model.login.UserResponse
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun login(body: UserBody): Flow<UserResponse>
}