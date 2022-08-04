package com.dionis.auladokevyn.data.api.registermember

import com.dionis.auladokevyn.domain.model.Members
import com.dionis.auladokevyn.model.RegisterMemberBody
import com.dionis.auladokevyn.model.RegisterMemberResponse
import javax.inject.Inject

class RegisterMemberApi @Inject constructor(private val registerMemberEndPoint: RegisterMemberEndPoint) {

    suspend fun newMember(body: RegisterMemberBody): RegisterMemberResponse =
        registerMemberEndPoint.newMember(body)
}
