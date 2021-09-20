package com.example.mobicatestapp.di

import com.example.mobicatestapp.ui.network_connection.NetworkStatusTracker
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val networkStatusModule = module {
    single { NetworkStatusTracker(context = androidContext()) }
}