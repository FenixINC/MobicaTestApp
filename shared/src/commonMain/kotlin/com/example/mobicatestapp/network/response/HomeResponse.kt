package com.example.mobicatestapp.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeResponse(
    @SerialName(value = "page") val page: PageResponse? = PageResponse()
)
