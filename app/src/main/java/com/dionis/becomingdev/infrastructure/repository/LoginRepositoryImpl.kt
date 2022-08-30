package com.dionis.becomingdev.infrastructure.repository

import com.dionis.becomingdev.data.api.login.UserApi
import com.dionis.becomingdev.infrastructure.interfaces.repository.LoginRepository
import com.dionis.becomingdev.model.login.UserBody
import com.dionis.becomingdev.model.login.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val userApi: UserApi) :
    LoginRepository {

    override suspend fun login(body: UserBody): Flow<UserResponse> = flow{
        emit(userApi.login(body))
    }


}