package com.dionis.auladokevyn.data.api

import com.dionis.auladokevyn.domain.model.Members
import retrofit2.http.GET

interface HomeEndPoint {

    @GET("developers")
    suspend fun getMembers(): Members

}