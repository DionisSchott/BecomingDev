//package com.dionis.becomingdev.presentation.viewModels
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.viewModelScope
//import com.dionis.becomingdev.base.BaseViewModel
//import com.dionis.becomingdev.base.States
//import com.dionis.becomingdev.infrastructure.interfaces.usecase.IProfileUseCase
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.flow.catch
//import kotlinx.coroutines.flow.flowOn
//import kotlinx.coroutines.flow.onStart
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//@HiltViewModel
//class ProfileViewModel @Inject constructor(private val profileUseCase: IProfileUseCase) : BaseViewModel() {
//
//    private var _getMembersResult = MutableLiveData<States.GetMembersState>()
//    val getMembersResult: LiveData<States.GetMembersState> = _getMembersResult
//
//    fun getMember() {
//        viewModelScope.launch {
//            profileUseCase.getMember()
//                .flowOn(Dispatchers.Main)
//                .onStart { _getMembersResult.value = States.GetMembersState.Loading }
//                .catch {
//                    _getMembersResult.value = States.GetMembersState.Failure(it.message.toString())
//                }
//                .collect { _getMembersResult.value = States.GetMembersState.Success(it) }
//        }
//    }
//
//
//}