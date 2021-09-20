package com.example.mobicatestapp.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TitleResponse(
    @SerialName(value = "id") val id: Long? = 0L,
    @SerialName(value = "value") val value: String? = "",
    @SerialName(value = "attributes") val attributes: AttributeResponse? = AttributeResponse()
)