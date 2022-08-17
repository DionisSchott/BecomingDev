package com.dionis.becomingdev.base

import com.dionis.becomingdev.domain.model.Members
import com.dionis.becomingdev.model.registerMember.RegisterMemberResponse

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

    sealed class ValidateAddNewMember {
        object NameEmpty : ValidateAddNewMember()
        object LastnameEmpty : ValidateAddNewMember()
        object AgeEmpty : ValidateAddNewMember()
        object TechnologyEmpty : ValidateAddNewMember()
        object ExperienceEmpty : ValidateAddNewMember()
        object SocielEmpty : ValidateAddNewMember()
        object EmailEmpty : ValidateAddNewMember()
        object ContactEmpty : ValidateAddNewMember()
        object FieldsDone : ValidateAddNewMember()

    }

}
