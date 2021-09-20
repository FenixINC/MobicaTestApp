package com.example.mobicatestapp.database.adapters

import com.example.mobicatestapp.database.entity.AttributeEntity
import com.example.mobicatestapp.mapper.AttributeMapper
import com.squareup.sqldelight.ColumnAdapter
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
val attributeAdapter = object : ColumnAdapter<AttributeEntity, String> {
    override fun decode(databaseValue: String): AttributeEntity =
        AttributeMapper.mapStringToEntity(titleString = databaseValue)

    override fun encode(value: AttributeEntity): String =
        AttributeMapper.mapEntityToString(titleEntity = value)
}