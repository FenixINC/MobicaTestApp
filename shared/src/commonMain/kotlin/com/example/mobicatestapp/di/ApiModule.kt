package com.example.mobicatestapp.di

import com.example.mobicatestapp.network.api.MobicaApi
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

internal val apiModule = DI.Module(
    name = "ApiModule",
    init = {
        bindSingleton {
            MobicaApi(
                httpClient = instance(),
                json = instance()
            )
        }
    }
)