package com.example.mobicatestapp.database.entity

import kotlinx.serialization.Serializable

@Serializable
data class DescriptionEntity(
    val value: String = "",
    val textColor: String = "",
    val fontSize: String = ""
)