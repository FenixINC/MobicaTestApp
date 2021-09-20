package com.example.mobicatestapp.database.adapters

import com.example.mobicatestapp.database.entity.DescriptionEntity
import com.example.mobicatestapp.mapper.DescriptionMapper
import com.squareup.sqldelight.ColumnAdapter
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
val descriptionAdapter = object : ColumnAdapter<DescriptionEntity, String> {
    override fun decode(databaseValue: String): DescriptionEntity =
        DescriptionMapper.mapStringToEntity(titleString = databaseValue)

    override fun encode(value: DescriptionEntity): String =
        DescriptionMapper.mapEntityToString(titleEntity = value)
}