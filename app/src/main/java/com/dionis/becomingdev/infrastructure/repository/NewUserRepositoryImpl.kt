package com.dionis.becomingdev.infrastructure.repository

import com.dionis.becomingdev.data.api.newuser.NewUserApi
import com.dionis.becomingdev.infrastructure.interfaces.repository.NewUserRepository
import com.dionis.becomingdev.model.newUser.NewUserBody
import com.dionis.becomingdev.model.newUser.NewUserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewUserRepositoryImpl @Inject constructor(private val newUserApi: NewUserApi) :
    NewUserRepository {

    override suspend fun newUser(body: NewUserBody): Flow<NewUserResponse> = flow{
        emit(newUserApi.newUser(body))

    }
}