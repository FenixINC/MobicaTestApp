package com.example.mobicatestapp.di

import kotlinx.serialization.json.Json
import org.kodein.di.DI
import org.kodein.di.bindSingleton

internal val serializationModule = DI.Module(
    name = "SerializationModule",
    init = {
        bindSingleton {
            Json {
                isLenient = true
                ignoreUnknownKeys = true
            }
        }
    }
)