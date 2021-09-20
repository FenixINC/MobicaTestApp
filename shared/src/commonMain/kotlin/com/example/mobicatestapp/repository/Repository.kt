package com.example.mobicatestapp.repository

import com.example.mobicatestapp.cache.CardEntity
import com.example.mobicatestapp.database.MobicaDatabase
import com.example.mobicatestapp.network.api.MobicaApi
import com.example.mobicatestapp.network.response.HomeResponse
import com.russhwolf.settings.Settings

class Repository(
    private val mobicaApi: MobicaApi,
    private val userSettings: Settings,
    private val database: MobicaDatabase
) {
    suspend fun loadHomeListNetwork(): HomeResponse {
        return mobicaApi.loadHomeList()
    }

    fun saveHomeListDatabase(cardList: List<CardEntity>): List<CardEntity> {
        return database.saveCardList(cardList = cardList)
    }

    fun loadHomeListDatabase(): List<CardEntity> {
        return database.loadCardList()
    }

    fun loadCardDetailsDatabaseById(cardId: Long): CardEntity {
        return database.loadCardDetailsById(cardId = cardId)
    }

    fun clearDatabase() {
        database.clearDatabase()
    }
}