package com.dionis.becomingdev.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dionis.becomingdev.base.BaseViewModel
import com.dionis.becomingdev.base.States
import com.dionis.becomingdev.domain.usecase.PostPhotoUseCase
import com.dionis.becomingdev.model.newUser.NewUserBody
import com.dionis.becomingdev.model.photos.PostPhotoBody
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject


@HiltViewModel
class PostPhotoViewModel @Inject internal constructor(
    private val postPhotoUseCase: PostPhotoUseCase
) : BaseViewModel() {

    private val _postPhoto = MutableLiveData<States.PostPhotoState>()
    val postPhoto: LiveData<States.PostPhotoState> = _postPhoto

    private val _validateFields: MutableLiveData<States.ValidatePostPhoto> = MutableLiveData()
    val validateFields: LiveData<States.ValidatePostPhoto> get() = _validateFields


    fun postPhoto(
        photo: File,
        developer_id: Int,
    ) {
        viewModelScope.launch {
            postPhotoUseCase.postPhoto(
                PostPhotoBody(
                    photo,
                    developer_id
                    ))
                .flowOn(Dispatchers.IO)
                .onStart { _postPhoto.value = States.PostPhotoState.Loading }
                .catch {
                    _postPhoto.value = States.PostPhotoState.Error(it.message.toString())
                }
                .collect { _postPhoto.value = States.PostPhotoState.Success(it) }
        }
    }

    fun validateFields(
        photo: File,
        developer_id: Int,
    ) {
        if (validateAllFields(
                photo,
                developer_id
            )
        )
            _validateFields.value = States.ValidatePostPhoto.FieldsDone
    }

    private fun validateAllFields(
        photo: File,
        developer_id: Int,

    ): Boolean {
        if (photo) {
            _validateFields.value = States.ValidatePostPhoto.UserPhotoEmpty
            return false
        }
        if (developer_id.toString().isEmpty()) {
            _validateFields.value = States.ValidatePostPhoto.UserIdEmpty
            return false
        }

        return true
    }

}