package com.example.mobicatestapp.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CardDetails(
    @SerialName(value = "value") val value: String? = "",
    @SerialName(value = "attributes") val attributes: AttributeResponse? = AttributeResponse(),
    @SerialName(value = "title") val titleResponse: TitleResponse? = TitleResponse(),
    @SerialName(value = "description") val descriptionResponse: DescriptionResponse? = DescriptionResponse(),
    @SerialName(value = "image") val imageResponse: ImageResponse? = ImageResponse()
)
