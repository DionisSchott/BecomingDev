package com.dionis.becomingdev.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dionis.becomingdev.base.BaseViewModel
import com.dionis.becomingdev.base.States
import com.dionis.becomingdev.domain.usecase.NewUserUseCase
import com.dionis.becomingdev.model.newUser.NewUserBody
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewUserViewModel @Inject internal constructor(
    private val newUserUseCase: NewUserUseCase,
) : BaseViewModel() {

    private val _addNewUser = MutableLiveData<States.AddNewUserState>()
    val addNewUser: LiveData<States.AddNewUserState> = _addNewUser

    private val _validateFields: MutableLiveData<States.ValidateAddNewUser> = MutableLiveData()
    val validateFields: LiveData<States.ValidateAddNewUser> get() = _validateFields

    fun newMember(
        name: String,
        email: String,
        password: String,
    ) {
        viewModelScope.launch {
            newUserUseCase.newUser(
                NewUserBody(
                    name,
                    email,
                    password))
                .flowOn(Dispatchers.IO)
                .onStart { _addNewUser.value = States.AddNewUserState.Loading }
                .catch {
                    _addNewUser.value = States.AddNewUserState.Error(it.message.toString())
                }
                .collect { _addNewUser.value = States.AddNewUserState.Success(it) }
        }
    }

    fun validateFields(
        name: String,
        email: String,
        password: String,
    ) {
        if (validateAllFields(
                name,
                email,
                password
            )
        )
            _validateFields.value = States.ValidateAddNewUser.FieldsDone
    }

    private fun validateAllFields(
        name: String,
        email: String,
        password: String,
    ): Boolean {
        if (name.isEmpty()) {
            _validateFields.value = States.ValidateAddNewUser.UserNameEmpty
            return false
        }
        if (email.isEmpty()) {
            _validateFields.value = States.ValidateAddNewUser.EmailEmpty
            return false
        }
        if (password.isEmpty()) {
            _validateFields.value = States.ValidateAddNewUser.PasswordEmpty
            return false
        }
        return true
    }


}