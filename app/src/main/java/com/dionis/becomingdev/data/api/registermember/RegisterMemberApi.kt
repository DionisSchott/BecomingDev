package com.dionis.becomingdev.data.api.registermember

import com.dionis.becomingdev.model.RegisterMemberBody
import com.dionis.becomingdev.model.RegisterMemberResponse
import javax.inject.Inject

class RegisterMemberApi @Inject constructor(private val registerMemberEndPoint: RegisterMemberEndPoint) {

    suspend fun newMember(body: RegisterMemberBody): RegisterMemberResponse =
        registerMemberEndPoint.newMember(body)
}
