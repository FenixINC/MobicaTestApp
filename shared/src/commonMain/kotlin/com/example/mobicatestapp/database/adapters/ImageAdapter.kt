package com.example.mobicatestapp.database.adapters

import com.example.mobicatestapp.database.entity.ImageEntity
import com.example.mobicatestapp.mapper.ImageMapper
import com.squareup.sqldelight.ColumnAdapter
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
val imageAdapter = object : ColumnAdapter<ImageEntity, String> {
    override fun decode(databaseValue: String): ImageEntity =
        ImageMapper.mapStringToEntity(titleString = databaseValue)

    override fun encode(value: ImageEntity): String =
        ImageMapper.mapEntityToString(titleEntity = value)
}