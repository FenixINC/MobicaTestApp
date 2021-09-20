package com.example.mobicatestapp.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CardResponse(
    @SerialName(value = "card_type") val cardType: String? = "",
    @SerialName(value = "card") val cardDetails: CardDetails? = CardDetails()
)