package com.dionis.becomingdev.data.api.registermember

import com.dionis.becomingdev.domain.model.MembersItem
import com.dionis.becomingdev.model.editMember.EditUserBody
import com.dionis.becomingdev.model.registerMember.RegisterMemberBody
import com.dionis.becomingdev.model.registerMember.RegisterMemberResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path

interface RegisterMemberEndPoint {

    @POST("developers")
    suspend fun registerMember(@Body body: RegisterMemberBody): RegisterMemberResponse

    @DELETE("developers/{memberId}")
    suspend fun deleteMember(@Path("memberId") id: Int): MembersItem
}