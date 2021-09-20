package com.example.mobicatestapp.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DescriptionResponse(
    @SerialName(value = "value") val value: String? = "",
    @SerialName(value = "attributes") val attributes: AttributeResponse? = AttributeResponse()
)
