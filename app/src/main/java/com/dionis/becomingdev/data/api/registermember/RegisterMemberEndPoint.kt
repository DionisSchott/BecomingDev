package com.dionis.becomingdev.data.api.registermember

import com.dionis.becomingdev.model.RegisterMemberBody
import com.dionis.becomingdev.model.RegisterMemberResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterMemberEndPoint {

    @POST("developers")
    suspend fun newMember(@Body body: RegisterMemberBody): RegisterMemberResponse
}