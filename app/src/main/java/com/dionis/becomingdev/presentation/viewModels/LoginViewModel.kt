package com.dionis.becomingdev.presentation.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dionis.becomingdev.base.BaseViewModel
import com.dionis.becomingdev.base.States
import com.dionis.becomingdev.data.storage.SessionManager
import com.dionis.becomingdev.domain.usecase.LoginUseCase
import com.dionis.becomingdev.model.login.UserBody
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject internal constructor(
    @ApplicationContext private val context: Context,
    private val loginUseCase: LoginUseCase,
) : BaseViewModel() {

    private val _loginDone = MutableLiveData<States.LoginState>()
    val loginDone: LiveData<States.LoginState> = _loginDone

    private val _validateFields: MutableLiveData<States.ValidateLogin> = MutableLiveData()
    val validateFields: LiveData<States.ValidateLogin> get() = _validateFields

    fun login(email: String, password: String) {
        viewModelScope.launch {
            loginUseCase.login(UserBody(email, password))
                .flowOn(Dispatchers.IO)
                .onStart { _loginDone.value = States.LoginState.Loading }
                .catch { _loginDone.value = States.LoginState.Error(it.message.toString())}
                .collect { _loginDone.value = States.LoginState.Success(it)
                SessionManager(context).setToken(it.token)
                SessionManager(context).setId(it.user.id)}
        }
    }


    fun validateFields(
        email: String,
        password: String,
    ) {
        if (validateAllFields(
                email,
                password
            )
        )
            _validateFields.value = States.ValidateLogin.FieldsDone
    }


    private fun validateAllFields(
        email: String,
        password: String,
    ): Boolean {
        if (email.isEmpty()) {
            _validateFields.value = States.ValidateLogin.EmailEmpty
            return false
        }
        if (password.isEmpty()) {
            _validateFields.value = States.ValidateLogin.PasswordEmpty
            return false
        }
        return true
    }

}
