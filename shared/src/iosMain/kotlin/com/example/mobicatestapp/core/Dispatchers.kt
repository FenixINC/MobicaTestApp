package com.example.mobicatestapp.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual var mainDispatcher: CoroutineDispatcher = Dispatchers.Main
actual val ioDispatcher: CoroutineDispatcher = Dispatchers.Main