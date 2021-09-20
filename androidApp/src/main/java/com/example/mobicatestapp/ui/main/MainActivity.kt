package com.example.mobicatestapp.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.core.view.WindowCompat
import com.example.mobicatestapp.core.navigation.MobicaNavigation
import com.example.mobicatestapp.core.resources.theme.MobicaTheme
import com.example.mobicatestapp.di.KodeinInjector
import com.example.mobicatestapp.di.viewModelModuleExt
import com.example.mobicatestapp.ui.network_connection.NetworkStatusViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@FlowPreview
@ExperimentalPagerApi
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@InternalCoroutinesApi
class MainActivity : ComponentActivity(), KoinComponent {

    private val networkStatusViewModel by inject<NetworkStatusViewModel>()

    private val mainViewModel = KodeinInjector.viewModelModuleExt.mainViewModel
    private val splashViewModel = KodeinInjector.viewModelModuleExt.splashViewModel
    private val homeViewModel = KodeinInjector.viewModelModuleExt.homeViewModel
    private val cardDetailsViewModel = KodeinInjector.viewModelModuleExt.cardDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val isDarkMode = remember { mutableStateOf(value = false) }

            MobicaTheme(
                isDarkThemeFromApp = isDarkMode.value,
                isDarkThemeFromSystem = false
            ) {
                MobicaNavigation(
                    mainViewModel = mainViewModel,
                    splashViewModel = splashViewModel,
                    homeViewModel = homeViewModel,
                    cardDetailsViewModel = cardDetailsViewModel,
                    isDarkMode = { isDarkMode.value = it }
                )
            }
        }
    }
}