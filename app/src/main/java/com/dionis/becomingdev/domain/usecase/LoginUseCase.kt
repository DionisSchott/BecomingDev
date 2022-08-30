package com.dionis.becomingdev.domain.usecase

import com.dionis.becomingdev.infrastructure.interfaces.repository.LoginRepository
import com.dionis.becomingdev.model.login.UserBody
import com.dionis.becomingdev.model.login.UserResponse
import kotlinx.coroutines.flow.Flow
import org.json.JSONObject
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {

    suspend fun login(body: UserBody): Flow<UserResponse> {
        return loginRepository.login(body)
    }





}