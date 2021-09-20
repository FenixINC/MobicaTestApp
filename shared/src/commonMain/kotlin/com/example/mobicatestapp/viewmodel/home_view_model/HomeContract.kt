package com.example.mobicatestapp.viewmodel.home_view_model

import com.example.mobicatestapp.cache.CardEntity
import com.example.mobicatestapp.viewmodel.base.ViewEffect
import com.example.mobicatestapp.viewmodel.base.ViewEvent
import com.example.mobicatestapp.viewmodel.base.ViewState

class HomeContract {
    sealed class HomeEvent : ViewEvent {
        object LoadHomeList : HomeEvent()

        sealed class Navigation : HomeEvent() {
            data class DetailsScreen(val itemId: Long) : Navigation()
        }
    }

    sealed class HomeEffect : ViewEffect {
        data class ShowSnackBar(val snackBarMessage: String) : HomeEffect()
        data class ShowToast(val toastMessage: String) : HomeEffect()

        sealed class Navigation : HomeEffect() {
            data class DetailsScreen(val itemId: Long) : Navigation()
        }
    }

    data class HomeState(
        val result: List<CardEntity>?,
        val isLoading: Boolean,
    ) : ViewState
}