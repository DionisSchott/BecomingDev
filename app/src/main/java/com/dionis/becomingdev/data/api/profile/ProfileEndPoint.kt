package com.dionis.becomingdev.data.api.profile

import com.dionis.becomingdev.domain.model.Members
import com.dionis.becomingdev.domain.model.MembersItem
import com.dionis.becomingdev.domain.model.UserInfoResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ProfileEndPoint {

    @GET("developers/{memberId}")
    suspend fun getUserInfo(@Path("memberId") id: Int): MembersItem

}