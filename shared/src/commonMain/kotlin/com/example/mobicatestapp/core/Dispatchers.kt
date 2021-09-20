package com.example.mobicatestapp.core

import kotlinx.coroutines.CoroutineDispatcher

expect var mainDispatcher: CoroutineDispatcher
expect val ioDispatcher: CoroutineDispatcher