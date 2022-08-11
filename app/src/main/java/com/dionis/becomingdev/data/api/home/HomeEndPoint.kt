package com.dionis.becomingdev.data.api.home

import com.dionis.becomingdev.domain.model.Members
import retrofit2.http.GET

interface HomeEndPoint {

    @GET("developers")
    suspend fun getMembers(): Members

}
