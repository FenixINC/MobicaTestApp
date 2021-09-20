package com.example.mobicatestapp.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FontResponse(
    @SerialName(value = "size") val size: Int? = 0
)