package com.example.mobicatestapp.viewmodel.card_details_view_model

import com.example.mobicatestapp.cache.CardEntity
import com.example.mobicatestapp.constants.StringConstants.EMPTY_TEXT
import com.example.mobicatestapp.database.entity.AttributeEntity
import com.example.mobicatestapp.database.entity.DescriptionEntity
import com.example.mobicatestapp.database.entity.ImageEntity
import com.example.mobicatestapp.database.entity.TitleEntity
import com.example.mobicatestapp.viewmodel.base.ViewEffect
import com.example.mobicatestapp.viewmodel.base.ViewEvent
import com.example.mobicatestapp.viewmodel.base.ViewState

class CardDetailsContract {
    sealed class CardDetailsEvent : ViewEvent {
        data class LoadCardDetails(val cardId: Long) : CardDetailsEvent()
    }

    sealed class CardDetailsEffect : ViewEffect {
        data class ShowSnackBar(val snackBarMessage: String) : CardDetailsEffect()
        data class ShowToast(val toastMessage: String) : CardDetailsEffect()
    }

    data class CardDetailsState(
        val isLoading: Boolean,
        val cardEntity: CardEntity = CardEntity(
            id = -1,
            cardType = EMPTY_TEXT,
            value = EMPTY_TEXT,
            attributeEntity = AttributeEntity(),
            titleEntity = TitleEntity(),
            descriptionEntity = DescriptionEntity(),
            imageEntity = ImageEntity()
        )
    ) : ViewState
}