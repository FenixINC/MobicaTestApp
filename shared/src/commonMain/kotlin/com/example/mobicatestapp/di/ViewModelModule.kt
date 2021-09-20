package com.example.mobicatestapp.di

import com.example.mobicatestapp.viewmodel.card_details_view_model.CardDetailsViewModel
import com.example.mobicatestapp.viewmodel.home_view_model.HomeViewModel
import com.example.mobicatestapp.viewmodel.main_view_model.MainViewModel
import com.example.mobicatestapp.viewmodel.splash_view_model.SplashViewModel
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import kotlin.native.concurrent.ThreadLocal

internal val viewModelModule = DI.Module(
    name = "ViewModelModule",
    init = {
        bindSingleton { MainViewModel() }
        bindSingleton { SplashViewModel() }
        bindSingleton { HomeViewModel(repository = instance()) }
        bindSingleton { CardDetailsViewModel(repository = instance()) }
    }
)

@ThreadLocal
object ViewModelModule {
    val mainViewModel: MainViewModel
        get() = KodeinInjector.di.instance()

    val splashViewModel: SplashViewModel
        get() = KodeinInjector.di.instance()

    val homeViewModel: HomeViewModel
        get() = KodeinInjector.di.instance()

    val cardDetailsViewModel: CardDetailsViewModel
        get() = KodeinInjector.di.instance()
}

val KodeinInjector.viewModelModuleExt: ViewModelModule
    get() = ViewModelModule