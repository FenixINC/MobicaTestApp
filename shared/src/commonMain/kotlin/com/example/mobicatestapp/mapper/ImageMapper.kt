package com.example.mobicatestapp.mapper

import com.example.mobicatestapp.database.entity.ImageEntity
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@ExperimentalSerializationApi
object ImageMapper {

    private val json: Json by lazy {
        Json {
            isLenient = true
            allowStructuredMapKeys = true
            ignoreUnknownKeys = true
        }
    }

    fun mapEntityToString(titleEntity: ImageEntity): String {
        return json.encodeToString(titleEntity)
    }

    fun mapStringToEntity(titleString: String): ImageEntity {
        return json.decodeFromString(titleString)
    }
}