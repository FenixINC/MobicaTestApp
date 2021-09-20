package com.example.mobicatestapp.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren

actual open class BaseViewModel actual constructor() {

    private val viewModelJob = SupervisorJob()
    val viewModelScope: CoroutineScope = CoroutineScope(context = ioDispatcher + viewModelJob)

    actual val commonViewModelScope: CoroutineScope = viewModelScope

    protected actual open fun onCleared() {
        viewModelJob.cancelChildren()
    }
}