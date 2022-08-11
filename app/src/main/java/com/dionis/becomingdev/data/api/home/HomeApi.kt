package com.dionis.becomingdev.data.api.home

import com.dionis.becomingdev.domain.model.Members
import javax.inject.Inject

class HomeApi @Inject constructor(private val homeEndPoint: HomeEndPoint)  {

    suspend fun getMembers(): Members = homeEndPoint.getMembers()

}