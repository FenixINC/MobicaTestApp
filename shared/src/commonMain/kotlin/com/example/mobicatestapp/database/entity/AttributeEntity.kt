package com.example.mobicatestapp.database.entity

import kotlinx.serialization.Serializable

@Serializable
data class AttributeEntity(
    val textColor: String = "",
    val fontSize: String = ""
)