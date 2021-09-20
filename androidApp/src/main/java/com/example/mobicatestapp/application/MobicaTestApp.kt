package com.example.mobicatestapp.application

import android.app.Application
import com.example.mobicatestapp.di.KodeinInjector
import com.example.mobicatestapp.di.networkStatusModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.util.*

@FlowPreview
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class MobicaTestApp : Application() {

    override fun onCreate() {
        super.onCreate()
        KodeinInjector.init(context = this@MobicaTestApp)
        startKoin {
            androidContext(androidContext = this@MobicaTestApp)
            modules(
                networkStatusModule
            )
        }
    }
}