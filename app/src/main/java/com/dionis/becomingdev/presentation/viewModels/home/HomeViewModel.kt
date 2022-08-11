package com.dionis.becomingdev.presentation.viewModels.home


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dionis.becomingdev.base.BaseViewModel
import com.dionis.becomingdev.base.States
import com.dionis.becomingdev.infrastructure.interfaces.usecase.IHomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeUseCase: IHomeUseCase): BaseViewModel() {

    private var _getMembersResult = MutableLiveData<States.GetMembersState>()
    val getMembersResult: LiveData<States.GetMembersState> = _getMembersResult

    fun getMembers(){
        viewModelScope.launch { homeUseCase.getMembers()
            .flowOn(Dispatchers.Main)
            .onStart { _getMembersResult.value = States.GetMembersState.Loading }
            .catch { _getMembersResult.value = States.GetMembersState.Failure(it.message.toString()) }
            .collect{_getMembersResult.value = States.GetMembersState.Success(it)}
        }
    }


}