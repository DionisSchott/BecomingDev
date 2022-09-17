package com.dionis.becomingdev.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dionis.becomingdev.base.BaseViewModel
import com.dionis.becomingdev.base.States
import com.dionis.becomingdev.infrastructure.interfaces.usecase.IProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.exp

@HiltViewModel
class ProfileViewModel @Inject constructor(private val profileUseCase: IProfileUseCase) : BaseViewModel() {

    private var _getUserResult = MutableLiveData<States.GetUserState>()
    val getUserResult: LiveData<States.GetUserState> = _getUserResult


    private val _validateFields: MutableLiveData<States.ValidateEditProfile> = MutableLiveData()
    val validateFields: LiveData<States.ValidateEditProfile> get() = _validateFields


    fun getUserInfo(memberId: Int) {

        viewModelScope.launch {
            profileUseCase.getUserInfo(memberId)
                .flowOn(Dispatchers.Main)
                .onStart { _getUserResult.value = States.GetUserState.Loading }
                .catch {
                    _getUserResult.value = States.GetUserState.Failure(it.message.toString())
                }
                .collect { _getUserResult.value = States.GetUserState.Success(it) }
        }
    }




    fun validateFields(
        name: String,
        mail: String,
        technology: String,
        experience: String,
        social: String,

    ) {
        if (validateAllFields(
                name,
                mail,
                technology,
                experience,
                social,

            )
        )
            _validateFields.value = States.ValidateEditProfile.FieldsDone

    }

    private fun validateAllFields(
        name: String,
        mail: String,
        technology: String,
        experience: String,
        social: String,

    ): Boolean {
        if (name.isEmpty()) {
            _validateFields.value = States.ValidateEditProfile.NameEmpty
            return false
        }
        if (mail.isEmpty()) {
            _validateFields.value = States.ValidateEditProfile.EmailEmpty
            return false
        }
        if (technology.isEmpty()) {
            _validateFields.value = States.ValidateEditProfile.TechnologyEmpty
            return false
        }
        if (experience.isEmpty()) {
            _validateFields.value = States.ValidateEditProfile.ExperinceEmpty
            return false
        }
        if (social.isEmpty()) {
            _validateFields.value = States.ValidateEditProfile.SocialEmpty
            return false
        }

        return true
    }


}