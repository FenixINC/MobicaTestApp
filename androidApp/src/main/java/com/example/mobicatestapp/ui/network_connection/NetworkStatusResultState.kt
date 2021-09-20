package com.example.mobicatestapp.ui.network_connection

sealed class NetworkStatusResultState {
    object Fetched : NetworkStatusResultState()
    object Error : NetworkStatusResultState()
}