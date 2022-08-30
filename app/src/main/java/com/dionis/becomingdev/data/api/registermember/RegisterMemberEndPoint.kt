package com.dionis.becomingdev.data.api.registermember

import com.dionis.becomingdev.model.registerMember.RegisterMemberBody
import com.dionis.becomingdev.model.registerMember.RegisterMemberResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterMemberEndPoint {

    @POST("developers")
    suspend fun registerMember(@Body body: RegisterMemberBody): RegisterMemberResponse
}