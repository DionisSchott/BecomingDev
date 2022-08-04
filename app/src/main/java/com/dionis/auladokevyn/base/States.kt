package com.dionis.auladokevyn.base

import com.dionis.auladokevyn.domain.model.Members
import com.dionis.auladokevyn.model.RegisterMemberResponse

abstract class States {

    sealed class GetMembersState{

        object Loading: GetMembersState()
        data class Success(val members : Members): GetMembersState()
        data class Failure(val error: String): GetMembersState()

    }

    sealed class AddNewMemberState {
        object Loading : AddNewMemberState()
        data class Success(val newMember: RegisterMemberResponse) : AddNewMemberState()
        data class Error(val error: String) : AddNewMemberState()
    }

}
