package com.dionis.becomingdev.data.api.registermember

import com.dionis.becomingdev.model.registerMember.RegisterMemberBody
import com.dionis.becomingdev.model.registerMember.RegisterMemberResponse
import javax.inject.Inject

class RegisterMemberApi @Inject constructor(private val registerMemberEndPoint: RegisterMemberEndPoint) {

    suspend fun registerMember(body: RegisterMemberBody): RegisterMemberResponse =
        registerMemberEndPoint.registerMember(body)
}
