package com.dionis.becomingdev.data.api.login

import com.dionis.becomingdev.model.login.UserBody
import com.dionis.becomingdev.model.login.UserResponse
import javax.inject.Inject

class UserApi @Inject constructor(private val userEndPoint: UserEndPoint) {

    suspend fun login(body: UserBody): UserResponse = userEndPoint.login(body)

}