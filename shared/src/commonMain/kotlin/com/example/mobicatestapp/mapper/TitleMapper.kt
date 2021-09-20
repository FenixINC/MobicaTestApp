package com.example.mobicatestapp.mapper

import com.example.mobicatestapp.database.entity.TitleEntity
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@ExperimentalSerializationApi
object TitleMapper {

    private val json: Json by lazy {
        Json {
            isLenient = true
            allowStructuredMapKeys = true
            ignoreUnknownKeys = true
        }
    }

    fun mapEntityToString(titleEntity: TitleEntity): String {
        return json.encodeToString(titleEntity)
    }

    fun mapStringToEntity(titleString: String): TitleEntity {
        return json.decodeFromString(titleString)
    }
}