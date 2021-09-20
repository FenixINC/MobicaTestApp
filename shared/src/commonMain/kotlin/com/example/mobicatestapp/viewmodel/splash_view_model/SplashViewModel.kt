package com.example.mobicatestapp.viewmodel.splash_view_model

import com.example.mobicatestapp.constants.DelayConstants.DELAY_SPLASH
import com.example.mobicatestapp.viewmodel.base.BaseVM
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel :
    BaseVM<SplashContract.SplashEvent, SplashContract.SplashState, SplashContract.SplashEffect>() {

    override fun setInitialState() = SplashContract.SplashState(isLoading = true)

    override fun handleEvents(event: SplashContract.SplashEvent) {
        when (event) {
            is SplashContract.SplashEvent.OpenHomeScreen -> {
                openHomeScreen()
            }
        }
    }

    private fun openHomeScreen() {
        commonViewModelScope.launch {
            delay(timeMillis = DELAY_SPLASH)
            setEffect {
                SplashContract.SplashEffect.OpenHomeScreen
            }
        }
    }
}