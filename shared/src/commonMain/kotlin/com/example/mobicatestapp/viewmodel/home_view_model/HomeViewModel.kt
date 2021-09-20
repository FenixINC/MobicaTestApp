package com.example.mobicatestapp.viewmodel.home_view_model

import com.example.mobicatestapp.cache.CardEntity
import com.example.mobicatestapp.constants.StringConstants.EMPTY_TEXT
import com.example.mobicatestapp.mapper.mapCardListResponseToEntity
import com.example.mobicatestapp.network.error_handling.HttpException
import com.example.mobicatestapp.repository.Repository
import com.example.mobicatestapp.viewmodel.base.BaseVM
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository) :
    BaseVM<HomeContract.HomeEvent, HomeContract.HomeState, HomeContract.HomeEffect>() {

    override fun setInitialState() = HomeContract.HomeState(result = null, isLoading = true)

    override fun handleEvents(event: HomeContract.HomeEvent) {
        when (event) {
            is HomeContract.HomeEvent.LoadHomeList -> {
                loadHomeListDatabase(
                    onSuccess = { cardList ->
                        setState {
                            copy(
                                result = cardList,
                                isLoading = false
                            )
                        }
                    },
                    onError = { errorMessage ->
                        setEffect { HomeContract.HomeEffect.ShowToast(toastMessage = errorMessage) }
                    }
                )
            }
            is HomeContract.HomeEvent.Navigation -> {
                when (event) {
                    is HomeContract.HomeEvent.Navigation.DetailsScreen -> {
                        setEffect {
                            HomeContract.HomeEffect.Navigation.DetailsScreen(itemId = event.itemId)
                        }
                    }
                }
            }
        }
    }

    private fun loadHomeListDatabase(
        onSuccess: (List<CardEntity>) -> Unit,
        onError: (String) -> Unit
    ) {
        commonViewModelScope.launch {
            kotlin.runCatching {
                repository.loadHomeListDatabase()
            }.onSuccess { cardList ->
                if (cardList.isNullOrEmpty()) {
                    loadHomeListNetwork(
                        onSuccess = { list -> onSuccess(list) },
                        onError = { errorMessage -> onError(errorMessage) }
                    )
                } else {
                    onSuccess(cardList)
                }
            }.onFailure { throwable ->
                onError(
                    when (throwable) {
                        is HttpException -> {
                            throwable.statusMessage ?: EMPTY_TEXT
                        }
                        else -> {
                            throwable.message ?: EMPTY_TEXT
                        }
                    }
                )
            }
        }
    }

    private fun loadHomeListNetwork(
        onSuccess: (List<CardEntity>) -> Unit,
        onError: (String) -> Unit
    ) {
        commonViewModelScope.launch {
            kotlin.runCatching {
                repository.loadHomeListNetwork()
            }.onSuccess { homeResponse ->
                kotlin.runCatching {
                    repository.saveHomeListDatabase(
                        cardList = mapCardListResponseToEntity(
                            cardList = homeResponse.page?.cardList ?: mutableListOf()
                        )
                    )
                }.onSuccess { cardList ->
                    onSuccess(cardList)
                }.onFailure { throwable ->
                    onError(
                        when (throwable) {
                            is HttpException -> {
                                throwable.statusMessage ?: EMPTY_TEXT
                            }
                            else -> {
                                throwable.message ?: EMPTY_TEXT
                            }
                        }
                    )
                }
            }.onFailure { throwable ->
                onError(
                    when (throwable) {
                        is HttpException -> {
                            throwable.statusMessage ?: EMPTY_TEXT
                        }
                        else -> {
                            throwable.message ?: EMPTY_TEXT
                        }
                    }
                )
            }
        }
    }
}