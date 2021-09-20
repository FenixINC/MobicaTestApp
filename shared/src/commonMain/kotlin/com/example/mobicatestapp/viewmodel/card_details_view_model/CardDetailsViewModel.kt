package com.example.mobicatestapp.viewmodel.card_details_view_model

import com.example.mobicatestapp.cache.CardEntity
import com.example.mobicatestapp.constants.StringConstants
import com.example.mobicatestapp.network.error_handling.HttpException
import com.example.mobicatestapp.repository.Repository
import com.example.mobicatestapp.viewmodel.base.BaseVM
import kotlinx.coroutines.launch

class CardDetailsViewModel(private val repository: Repository) :
    BaseVM<CardDetailsContract.CardDetailsEvent, CardDetailsContract.CardDetailsState, CardDetailsContract.CardDetailsEffect>() {

    override fun setInitialState() = CardDetailsContract.CardDetailsState(isLoading = true)

    override fun handleEvents(event: CardDetailsContract.CardDetailsEvent) {
        when (event) {
            is CardDetailsContract.CardDetailsEvent.LoadCardDetails -> {
                loadCardDetailsDatabase(cardId = event.cardId,
                    onSuccess = { cardEntity ->
                        setState {
                            copy(
                                isLoading = false,
                                cardEntity = cardEntity
                            )
                        }
                    },
                    onError = { errorMessage ->
                        setEffect { CardDetailsContract.CardDetailsEffect.ShowToast(toastMessage = errorMessage) }
                    }
                )
            }
        }
    }

    private fun loadCardDetailsDatabase(
        cardId: Long,
        onSuccess: (CardEntity) -> Unit,
        onError: (String) -> Unit
    ) {
        commonViewModelScope.launch {
            kotlin.runCatching {
                repository.loadCardDetailsDatabaseById(cardId = cardId)
            }.onSuccess { cardEntity ->
                onSuccess(cardEntity)
            }.onFailure { throwable ->
                onError(
                    when (throwable) {
                        is HttpException -> {
                            throwable.statusMessage ?: StringConstants.EMPTY_TEXT
                        }
                        else -> {
                            throwable.message ?: StringConstants.EMPTY_TEXT
                        }
                    }
                )
            }
        }
    }
}