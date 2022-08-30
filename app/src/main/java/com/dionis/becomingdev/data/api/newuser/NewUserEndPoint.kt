package com.dionis.becomingdev.data.api.newuser

import com.dionis.becomingdev.model.newUser.NewUserBody
import com.dionis.becomingdev.model.newUser.NewUserResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface NewUserEndPoint {

    @POST("users")
    suspend fun newUser(@Body body: NewUserBody): NewUserResponse
}