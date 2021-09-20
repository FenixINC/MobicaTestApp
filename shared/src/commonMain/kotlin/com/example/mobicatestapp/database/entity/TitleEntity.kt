package com.example.mobicatestapp.database.entity

import kotlinx.serialization.Serializable

@Serializable
data class TitleEntity(
    val value: String = "",
    val textColor: String = "",
    val fontSize: String = ""
)
