package com.example.mobicatestapp.network.error_handling

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    @SerialName(value = "name") val name: String? = "",
    @SerialName(value = "message") val message: String? = "",
    @SerialName(value = "code") val code: String? = ""
)