package com.dionis.auladokevyn.base

import com.dionis.auladokevyn.domain.model.Members

abstract class States {

    sealed class GetMembersState{

        object Loading: GetMembersState()
        data class Success(val members : Members): GetMembersState()
        data class Failure(val error: String): GetMembersState()

    }

}
