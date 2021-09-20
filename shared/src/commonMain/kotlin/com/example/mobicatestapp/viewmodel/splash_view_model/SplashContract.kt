package com.example.mobicatestapp.viewmodel.splash_view_model

import com.example.mobicatestapp.viewmodel.base.ViewEffect
import com.example.mobicatestapp.viewmodel.base.ViewEvent
import com.example.mobicatestapp.viewmodel.base.ViewState

class SplashContract {
    sealed class SplashEvent : ViewEvent {
        object OpenHomeScreen : SplashEvent()
    }

    sealed class SplashEffect : ViewEffect {
        object OpenHomeScreen : SplashEffect()
    }

    data class SplashState(
        val isLoading: Boolean
    ) : ViewState
}