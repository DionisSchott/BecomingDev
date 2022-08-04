package com.dionis.auladokevyn.presentation.viewModels.registerMember

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dionis.auladokevyn.base.BaseViewModel
import com.dionis.auladokevyn.base.States
import com.dionis.auladokevyn.domain.usecase.RegisterMemberUseCase
import com.dionis.auladokevyn.model.RegisterMemberBody
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

    private val _addNewMember = MutableLiveData<States.AddNewMemberState>()
    val addNewMember: LiveData<States.AddNewMemberState> = _addNewMember

    fun registerMember(
        name: String,
        lastname: String,
        age: String,
        technology: String,
        experience: String,
        socials: String,
        email: String,
        contact: String,
    ) {
        viewModelScope.launch {
            registerMemberUseCase.newMember(
                RegisterMemberBody(name, lastname, age, technology, experience, socials, email, contact))
                .flowOn(Dispatchers.IO)
                .onStart { _addNewMember.value = States.AddNewMemberState.Loading }
                .catch { _addNewMember.value = States.AddNewMemberState.Error(it.message.toString()) }
                .collect { _addNewMember.value = States.AddNewMemberState.Success(it) }
        }
    }

}