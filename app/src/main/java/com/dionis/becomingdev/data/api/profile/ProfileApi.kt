package com.dionis.becomingdev.data.api.profile

import com.dionis.becomingdev.domain.model.MembersItem
import javax.inject.Inject

class ProfileApi @Inject constructor(private val profileEndPoint: ProfileEndPoint)  {

    suspend fun getUserInfo(memberId: Int): MembersItem = profileEndPoint.getUserInfo(memberId)

}