package com.dionis.becomingdev.presentation.viewModels.registerMember

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dionis.becomingdev.base.BaseViewModel
import com.dionis.becomingdev.base.States
import com.dionis.becomingdev.domain.usecase.RegisterMemberUseCase
import com.dionis.becomingdev.model.RegisterMemberBody
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart


@HiltViewModel
class RegisterMemberViewModel @Inject internal constructor(
    private val registerMemberUseCase: RegisterMemberUseCase,
) : BaseViewModel() {

//    private val _validateFields = MutableLiveData<States.ValidateAddNewMemberState>() = MutableLiveData()
//    val validateFields: LiveData<States.ValidateAddNewMemberState> get() = _validateFields

    private val _addNewMember = MutableLiveData<States.AddNewMemberState>()
    val addNewMember: LiveData<States.AddNewMemberState> = _addNewMember

    private val _validateFields: MutableLiveData<States.ValidateAddNewMember> = MutableLiveData()
    val validateFields: LiveData<States.ValidateAddNewMember> get() = _validateFields


    fun registerMember(
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
            registerMemberUseCase.newMember(
                RegisterMemberBody(name,
                    lastname,
                    age,
                    technology,
                    experience,
                    socials,
                    email,
                    contact))
                .flowOn(Dispatchers.IO)
                .onStart { _addNewMember.value = States.AddNewMemberState.Loading }
                .catch {
                    _addNewMember.value = States.AddNewMemberState.Error(it.message.toString())
                }
                .collect { _addNewMember.value = States.AddNewMemberState.Success(it) }
        }
    }

    fun validateFields(
        name: String,
        lastname: String,
        age: String,
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
            _validateFields.value = States.ValidateAddNewMember.FieldsDone

    }

    private fun validateAllFields(
        name: String,
        lastname: String,
        age: String,
        technology: String,
        experience: String,
        socials: String,
        email: String,
        contact: String,
    ): Boolean {
        if (name.isEmpty()) {
            _validateFields.value = States.ValidateAddNewMember.NameEmpty
            return false
        }
        if (lastname.isEmpty()) {
            _validateFields.value = States.ValidateAddNewMember.LastnameEmpty
            return false
        }
        if (age.isEmpty()) {
            _validateFields.value = States.ValidateAddNewMember.AgeEmpty
            return false
        }
        if (technology.isEmpty()) {
            _validateFields.value = States.ValidateAddNewMember.TechnologyEmpty
            return false
        }
        if (experience.isEmpty()) {
            _validateFields.value = States.ValidateAddNewMember.ExperienceEmpty
            return false
        }
        if (socials.isEmpty()) {
            _validateFields.value = States.ValidateAddNewMember.SocielEmpty
            return false
        }
        if (email.isEmpty()) {
            _validateFields.value = States.ValidateAddNewMember.EmailEmpty
            return false
        }
        if (contact.isEmpty()) {
            _validateFields.value = States.ValidateAddNewMember.ContactEmpty
            return false
        }

        return true
    }

}