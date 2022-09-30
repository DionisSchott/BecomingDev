package com.dionis.becomingdev.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dionis.becomingdev.base.BaseViewModel
import com.dionis.becomingdev.base.States
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
class ProfileViewModel @Inject constructor(private val profileUseCase: IProfileUseCase) : BaseViewModel() {

    private var _editUser = MutableLiveData<States.EditUserState>()
    val editUser: LiveData<States.EditUserState> = _editUser


    fun editUser(
        memberId: Int,
        name: String,
        lastname: String,
        age: Int,
        technology: String,
        experience: String,
        socials: String,
        email: String,
        contact: String

    ) {

        viewModelScope.launch {
            profileUseCase.editUser(memberId, EditUserBody(name, lastname,age, technology, experience, socials,email, contact))
                .flowOn(Dispatchers.Main)
                .onStart { _editUser.value = States.EditUserState.Loading }
                .catch {
                    _editUser.value = States.EditUserState.Failure(it.message.toString())
                }
                .collect { _editUser.value = States.EditUserState.Success(it) }
        }
    }


}