package com.example.mobicatestapp.ui.network_connection

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged

@ExperimentalCoroutinesApi
class NetworkStatusTracker(context: Context) {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val networkStatus = callbackFlow<NetworkStatus> {
        val networkStatusCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onUnavailable() {
                println("onUnavailable")
                trySend(NetworkStatus.Unavailable)
            }

            override fun onAvailable(network: Network) {
                println("onAvailable")
                trySend(NetworkStatus.Available)
            }

            override fun onLost(network: Network) {
                println("onLost")
                trySend(NetworkStatus.Unavailable)
            }
        }

        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        connectivityManager.registerNetworkCallback(request, networkStatusCallback)

        if (connectivityManager.activeNetwork == null) {
            networkStatusCallback.onUnavailable()
        }

        awaitClose {
            connectivityManager.unregisterNetworkCallback(networkStatusCallback)
        }
    }.distinctUntilChanged()
}