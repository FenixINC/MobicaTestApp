package com.example.mobicatestapp.core

import kotlinx.coroutines.CoroutineScope

expect open class BaseViewModel() {

    val commonViewModelScope: CoroutineScope

    protected open fun onCleared()
}