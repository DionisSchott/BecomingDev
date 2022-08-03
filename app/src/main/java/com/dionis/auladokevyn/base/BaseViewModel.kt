package com.dionis.auladokevyn.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel (): ViewModel(), LifecycleObserver {
    open val nonBlockingLoading = MutableLiveData<Boolean>().apply { value = false}

}