package com.dionis.becomingdev.data.api.login

import com.dionis.becomingdev.model.login.UserBody
import com.dionis.becomingdev.model.login.UserResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserEndPoint {

    @POST("tokens")
    suspend fun login(@Body body: UserBody): UserResponse
}