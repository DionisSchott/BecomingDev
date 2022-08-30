package com.dionis.becomingdev.domain.usecase

import com.dionis.becomingdev.infrastructure.interfaces.repository.NewUserRepository
import com.dionis.becomingdev.model.newUser.NewUserBody
import com.dionis.becomingdev.model.newUser.NewUserResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewUserUseCase @Inject constructor(private val newUserRepository: NewUserRepository) {

    suspend fun newUser(body: NewUserBody): Flow<NewUserResponse> {
        return newUserRepository.newUser(body)
    }
}