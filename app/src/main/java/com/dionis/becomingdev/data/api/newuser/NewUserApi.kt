package com.dionis.becomingdev.data.api.newuser

import com.dionis.becomingdev.model.newUser.NewUserBody
import com.dionis.becomingdev.model.newUser.NewUserResponse
import javax.inject.Inject

class NewUserApi @Inject constructor(private val newUserEndPoint: NewUserEndPoint){

    suspend fun newUser(body: NewUserBody): NewUserResponse = newUserEndPoint.newUser(body)
}