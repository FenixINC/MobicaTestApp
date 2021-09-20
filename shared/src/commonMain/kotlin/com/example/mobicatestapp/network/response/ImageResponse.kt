package com.example.mobicatestapp.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageResponse(
    @SerialName(value = "url") val url: String? = "",
    @SerialName(value = "size") val sizeResponse: SizeResponse? = SizeResponse()
)