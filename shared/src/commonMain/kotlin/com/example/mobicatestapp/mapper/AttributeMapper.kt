package com.example.mobicatestapp.mapper

import com.example.mobicatestapp.database.entity.AttributeEntity
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@ExperimentalSerializationApi
object AttributeMapper {

    private val json: Json by lazy {
        Json {
            isLenient = true
            allowStructuredMapKeys = true
            ignoreUnknownKeys = true
        }
    }

    fun mapEntityToString(titleEntity: AttributeEntity): String {
        return json.encodeToString(titleEntity)
    }

    fun mapStringToEntity(titleString: String): AttributeEntity {
        return json.decodeFromString(titleString)
    }
}