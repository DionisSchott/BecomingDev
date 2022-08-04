package com.dionis.auladokevyn.data.api.home

import com.dionis.auladokevyn.domain.model.Members
import javax.inject.Inject

class HomeApi @Inject constructor(private val homeEndPoint: HomeEndPoint)  {

    suspend fun getMembers(): Members = homeEndPoint.getMembers()

}