package com.dionis.becomingdev.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dionis.becomingdev.base.BaseViewModel
import com.dionis.becomingdev.base.States
import com.dionis.becomingdev.domain.model.MembersItem
import com.dionis.becomingdev.infrastructure.interfaces.usecase.IProfileUseCase
import com.dionis.becomingdev.model.editMember.EditUserBody
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val profileUseCase: IProfileUseCase) :
    BaseViewModel() {

    private var _editUser = MutableLiveData<States.EditMemberState>()
    val editUser: LiveData<States.EditMemberState> = _editUser

    private var _newUserInfo = MutableLiveData<MembersItem>()
    val newUserInfo: LiveData<MembersItem> = _newUserInfo

    private val _validateFields: MutableLiveData<States.ValidateEditMember> = MutableLiveData()
    val validateFields: LiveData<States.ValidateEditMember> get() = _validateFields

    fun setNewUserInfo(any: MembersItem) {
        _newUserInfo.value = any
    }

    fun editUser(
        memberId: Int,
        name: String,
        lastname: String,
        age: Int,
        technology: String,
        experience: String,
        socials: String,
        email: String,
        contact: String,

        ) {

        viewModelScope.launch {
            profileUseCase.editUser(
                memberId,
                EditUserBody(name, lastname, age, technology, experience, socials, email, contact))
                .flowOn(Dispatchers.Main)
                .onStart { _editUser.value = States.EditMemberState.Loading }
                .catch {
                    _editUser.value = States.EditMemberState.Failure(it.message.toString())
                }
                .collect { _editUser.value = States.EditMemberState.Success(it) }
        }
    }


    fun validateFields(
        name: String,
        lastname: String,
        age: Int,
        technology: String,
        experience: String,
        socials: String,
        email: String,
        contact: String,
    ) {
        if (validateAllFields(
                name,
                lastname,
                age,
                technology,
                experience,
                socials,
                email,
                contact
            )
        )
            _validateFields.value = States.ValidateEditMember.FieldsDone

    }

    private fun validateAllFields(
        name: String,
        lastname: String,
        age: Int,
        technology: String,
        experience: String,
        socials: String,
        email: String,
        contact: String,
    ): Boolean {
        if (name.isEmpty()) {
            _validateFields.value = States.ValidateEditMember.NameEmpty
            return false
        }
        if (lastname.isEmpty()) {
            _validateFields.value = States.ValidateEditMember.LastnameEmpty
            return false
        }
        if (age.toString().isEmpty()) {
            _validateFields.value = States.ValidateEditMember.AgeEmpty
            return false
        }
        if (technology.isEmpty()) {
            _validateFields.value = States.ValidateEditMember.TechnologyEmpty
            return false
        }
        if (experience.isEmpty()) {
            _validateFields.value = States.ValidateEditMember.ExperienceEmpty
            return false
        }
        if (socials.isEmpty()) {
            _validateFields.value = States.ValidateEditMember.SocielEmpty
            return false
        }
        if (email.isEmpty()) {
            _validateFields.value = States.ValidateEditMember.EmailEmpty
            return false
        }
        if (contact.isEmpty()) {
            _validateFields.value = States.ValidateEditMember.ContactEmpty
            return false
        }
        return true
    }


}