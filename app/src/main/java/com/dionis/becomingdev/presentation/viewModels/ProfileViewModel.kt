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

@HiltViewModel
class ProfileViewModel @Inject constructor(private val profileUseCase: IProfileUseCase) : BaseViewModel() {

    private var _getUserResult = MutableLiveData<States.GetUserState>()
    val getUserResult: LiveData<States.GetUserState> = _getUserResult



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


}