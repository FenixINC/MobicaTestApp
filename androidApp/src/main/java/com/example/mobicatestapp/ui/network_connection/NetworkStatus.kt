package com.example.mobicatestapp.ui.network_connection

sealed class NetworkStatus {
    object Available : NetworkStatus()
    object Unavailable : NetworkStatus()
}