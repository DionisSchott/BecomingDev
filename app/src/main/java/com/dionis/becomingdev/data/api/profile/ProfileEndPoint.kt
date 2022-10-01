package com.dionis.becomingdev.data.api.profile

import com.dionis.becomingdev.domain.model.Members
import com.dionis.becomingdev.domain.model.MembersItem
import com.dionis.becomingdev.model.editMember.EditUserBody
import com.dionis.becomingdev.model.registerMember.RegisterMemberResponse
import retrofit2.http.*

interface ProfileEndPoint {

    @PUT("developers/{memberId}")
    suspend fun editUser(
        @Path("memberId") id: Int,
        @Body body: EditUserBody): MembersItem

}