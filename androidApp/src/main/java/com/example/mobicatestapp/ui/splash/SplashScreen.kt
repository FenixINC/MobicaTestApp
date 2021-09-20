package com.example.mobicatestapp.ui.splash

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.mobicatestapp.R
import com.example.mobicatestapp.viewmodel.splash_view_model.SplashContract
import com.example.mobicatestapp.viewmodel.splash_view_model.SplashViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun SplashScreen(
    splashViewModel: SplashViewModel,
    openHomeScreen: () -> Unit,
    navigateUpFinish: () -> Unit?
) {
    BackHandler(onBack = { navigateUpFinish() })

    LaunchedEffect(key1 = "key_splash_screen") {
        splashViewModel.effect
            .onEach { splashEffect ->
                when (splashEffect) {
                    is SplashContract.SplashEffect.OpenHomeScreen -> {
                        openHomeScreen()
                    }
                }
            }
            .collect()
    }

    splashViewModel.setEvent(event = SplashContract.SplashEvent.OpenHomeScreen)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_mobica_logo),
            contentDescription = "App Logo"
        )
    }
}