package com.dionis.becomingdev.data.api.profile

import com.dionis.becomingdev.domain.model.MembersItem
import com.dionis.becomingdev.model.editMember.EditUserBody
import javax.inject.Inject

class ProfileApi @Inject constructor(private val profileEndPoint: ProfileEndPoint)  {

    suspend fun editUser(memberId: Int, body: EditUserBody): MembersItem =
        profileEndPoint.editUser(memberId, body)

}