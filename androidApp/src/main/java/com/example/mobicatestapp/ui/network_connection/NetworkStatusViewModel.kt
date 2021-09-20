package com.example.mobicatestapp.ui.network_connection

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
class NetworkStatusViewModel(
    networkStatusTracker: NetworkStatusTracker,
) : ViewModel() {

    var state = networkStatusTracker.networkStatus
        .map(
            onUnavailable = { NetworkStatusResultState.Error },
            onAvailable = { NetworkStatusResultState.Fetched },
        )
}