package com.example.mobicatestapp.database.entity

import kotlinx.serialization.Serializable

@Serializable
data class ImageEntity(
    val url: String = "",
    val width: Int = 0,
    val height: Int = 0
)
