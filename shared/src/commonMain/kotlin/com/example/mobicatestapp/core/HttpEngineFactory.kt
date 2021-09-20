package com.example.mobicatestapp.core

import io.ktor.client.engine.*

expect class HttpEngineFactory constructor() {
    fun createEngine(): HttpClientEngineFactory<HttpClientEngineConfig>
}