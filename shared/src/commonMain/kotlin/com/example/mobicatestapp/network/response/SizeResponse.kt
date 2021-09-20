package com.example.mobicatestapp.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SizeResponse(
    @SerialName(value = "width") val width: Int? = 0,
    @SerialName(value = "height") val height: Int? = 0
)