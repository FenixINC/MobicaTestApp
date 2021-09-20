package com.example.mobicatestapp.di

import com.example.mobicatestapp.ui.network_connection.NetworkStatusViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.dsl.module

@FlowPreview
@ExperimentalCoroutinesApi
val viewModelModule = module {
    single { NetworkStatusViewModel(networkStatusTracker = get()) }
}