package com.example.mobicatestapp.viewmodel.main_view_model

import com.example.mobicatestapp.viewmodel.base.ViewEffect
import com.example.mobicatestapp.viewmodel.base.ViewEvent
import com.example.mobicatestapp.viewmodel.base.ViewState

class MainContract {
    sealed class MainEvent : ViewEvent {
        object CheckInternetConnection : MainEvent()
    }

    sealed class MainEffect : ViewEffect {
        data class ShowToast(val toastMessage: String) : MainEffect()
    }

    data class MainState(
        val globalData: String
    ) : ViewState
}