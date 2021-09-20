package com.example.mobicatestapp.mapper

import com.example.mobicatestapp.database.entity.DescriptionEntity
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@ExperimentalSerializationApi
object DescriptionMapper {

    private val json: Json by lazy {
        Json {
            isLenient = true
            allowStructuredMapKeys = true
            ignoreUnknownKeys = true
        }
    }

    fun mapEntityToString(titleEntity: DescriptionEntity): String {
        return json.encodeToString(titleEntity)
    }

    fun mapStringToEntity(titleString: String): DescriptionEntity {
        return json.decodeFromString(titleString)
    }
}