package com.dionis.becomingdev.base

import com.dionis.becomingdev.domain.model.Members
import com.dionis.becomingdev.domain.model.MembersItem
import com.dionis.becomingdev.model.login.UserResponse
import com.dionis.becomingdev.model.newUser.NewUserResponse
import com.dionis.becomingdev.model.photos.PostPhotoResponse
import com.dionis.becomingdev.model.registerMember.RegisterMemberResponse

abstract class States {

    sealed class GetMembersState {

        object Loading : GetMembersState()
        data class Success(val members: Members) : GetMembersState()
        data class Failure(val error: String) : GetMembersState()

    }

    sealed class EditMemberState {

        object Loading : EditMemberState()
        data class Success(val members: MembersItem) : EditMemberState()
        data class Failure(val error: String) : EditMemberState()

    }



    sealed class AddNewMemberState {
        object Loading : AddNewMemberState()
        data class Success(val newMember: RegisterMemberResponse) : AddNewMemberState()
        data class Error(val error: String) : AddNewMemberState()
    }

    sealed class DeleteMemberState {
        object Loading : DeleteMemberState()
        data class Success(val deleteMember: Int) : DeleteMemberState()
        data class Error(val error: String) : DeleteMemberState()
    }

    sealed class AddNewUserState {
        object Loading : AddNewUserState()
        data class Success(val newUser: NewUserResponse) : AddNewUserState()
        data class Error(val error: String) : AddNewUserState()
    }

    sealed class LoginState {
        object Loading : LoginState()
        data class Success(val login: UserResponse) : LoginState()
        data class Error(val error: String) : LoginState()
    }

    sealed class PostPhotoState {
        object Loading : PostPhotoState()
        data class Success(val login: PostPhotoResponse) : PostPhotoState()
        data class Error(val error: String) : PostPhotoState()
    }



    sealed class ValidateAddNewUser {
        object UserNameEmpty : ValidateAddNewUser()
        object EmailEmpty : ValidateAddNewUser()
        object PasswordEmpty : ValidateAddNewUser()
        object FieldsDone : ValidateAddNewUser()

    }

    sealed class ValidatePostPhoto {
        object UserPhotoEmpty : ValidatePostPhoto()
        object UserIdEmpty : ValidatePostPhoto()
        object FieldsDone : ValidatePostPhoto()

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

    sealed class ValidateEditMember {
        object NameEmpty : ValidateEditMember()
        object LastnameEmpty : ValidateEditMember()
        object AgeEmpty : ValidateEditMember()
        object TechnologyEmpty : ValidateEditMember()
        object ExperienceEmpty : ValidateEditMember()
        object SocielEmpty : ValidateEditMember()
        object EmailEmpty : ValidateEditMember()
        object ContactEmpty : ValidateEditMember()
        object FieldsDone : ValidateEditMember()

    }

    sealed class ValidateLogin {
        object EmailEmpty : ValidateLogin()
        object PasswordEmpty : ValidateLogin()
        object FieldsDone : ValidateLogin()
    }

}
