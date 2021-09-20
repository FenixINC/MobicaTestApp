package com.example.mobicatestapp.mapper

import com.example.mobicatestapp.cache.CardEntity
import com.example.mobicatestapp.constants.NumberConstants.EMPTY_NUMBER
import com.example.mobicatestapp.constants.StringConstants.EMPTY_TEXT
import com.example.mobicatestapp.database.entity.AttributeEntity
import com.example.mobicatestapp.database.entity.DescriptionEntity
import com.example.mobicatestapp.database.entity.ImageEntity
import com.example.mobicatestapp.database.entity.TitleEntity
import com.example.mobicatestapp.network.response.CardResponse

fun mapCardListResponseToEntity(cardList: List<CardResponse>): List<CardEntity> =
    cardList.map { cardResponse ->
        CardEntity(
            id = EMPTY_NUMBER.toLong(),
            value = cardResponse.cardDetails?.value ?: EMPTY_TEXT,
            cardType = cardResponse.cardType ?: EMPTY_TEXT,
            attributeEntity = AttributeEntity(
                textColor = cardResponse.cardDetails?.attributes?.textColor ?: EMPTY_TEXT,
                fontSize = cardResponse.cardDetails?.attributes?.font?.size?.toString() ?: EMPTY_TEXT
            ),
            titleEntity = TitleEntity(
                value = cardResponse.cardDetails?.titleResponse?.value ?: EMPTY_TEXT,
                textColor = cardResponse.cardDetails?.titleResponse?.attributes?.textColor
                    ?: EMPTY_TEXT,
                fontSize = cardResponse.cardDetails?.titleResponse?.attributes?.font?.size?.toString()
                    ?: EMPTY_TEXT
            ),
            descriptionEntity = DescriptionEntity(
                value = cardResponse.cardDetails?.descriptionResponse?.value ?: EMPTY_TEXT,
                textColor = cardResponse.cardDetails?.descriptionResponse?.attributes?.textColor
                    ?: EMPTY_TEXT,
                fontSize = cardResponse.cardDetails?.descriptionResponse?.attributes?.font?.size?.toString()
                    ?: EMPTY_TEXT
            ),
            imageEntity = ImageEntity(
                url = cardResponse.cardDetails?.imageResponse?.url ?: EMPTY_TEXT,
                width = cardResponse.cardDetails?.imageResponse?.sizeResponse?.width
                    ?: EMPTY_NUMBER,
                height = cardResponse.cardDetails?.imageResponse?.sizeResponse?.height
                    ?: EMPTY_NUMBER
            )
        )
    }