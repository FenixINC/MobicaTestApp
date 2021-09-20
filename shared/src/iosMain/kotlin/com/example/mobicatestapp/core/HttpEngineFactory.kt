package com.example.mobicatestapp.core

import io.ktor.client.engine.*
import io.ktor.client.engine.ios.*

actual class HttpEngineFactory {
    actual fun createEngine(): HttpClientEngineFactory<HttpClientEngineConfig> {
        return Ios
    }
}