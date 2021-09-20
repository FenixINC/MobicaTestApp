package com.example.mobicatestapp.database.adapters

import com.example.mobicatestapp.database.entity.TitleEntity
import com.example.mobicatestapp.mapper.TitleMapper
import com.squareup.sqldelight.ColumnAdapter
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
val titleAdapter = object : ColumnAdapter<TitleEntity, String> {
    override fun decode(databaseValue: String): TitleEntity =
        TitleMapper.mapStringToEntity(titleString = databaseValue)

    override fun encode(value: TitleEntity): String =
        TitleMapper.mapEntityToString(titleEntity = value)
}