package com.dionis.becomingdev.infrastructure.interfaces.repository

import com.dionis.becomingdev.model.newUser.NewUserBody
import com.dionis.becomingdev.model.newUser.NewUserResponse
import kotlinx.coroutines.flow.Flow

interface NewUserRepository {
    suspend fun newUser(body: NewUserBody):  Flow<NewUserResponse>
}