package com.dionis.auladokevyn.data.api.registermember

import com.dionis.auladokevyn.domain.model.Members
import com.dionis.auladokevyn.model.RegisterMemberBody
import com.dionis.auladokevyn.model.RegisterMemberResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterMemberEndPoint {

    @POST("developers")
    suspend fun newMember(@Body body: RegisterMemberBody): RegisterMemberResponse
}