package com.example.mobicatestapp.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AttributeResponse(
    @SerialName(value = "text_color") val textColor: String? = "",
    @SerialName(value = "font") val font: FontResponse? = FontResponse()
)
