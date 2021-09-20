package com.example.mobicatestapp.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PageResponse(
    @SerialName(value = "cards") val cardList: List<CardResponse>? = mutableListOf()
)
