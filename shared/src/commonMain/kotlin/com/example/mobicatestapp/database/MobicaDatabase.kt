package com.example.mobicatestapp.database

import com.example.mobicatestapp.cache.CardEntity
import com.example.mobicatestapp.cache.MobicaDatabase
import com.example.mobicatestapp.database.adapters.attributeAdapter
import com.example.mobicatestapp.database.adapters.descriptionAdapter
import com.example.mobicatestapp.database.adapters.imageAdapter
import com.example.mobicatestapp.database.adapters.titleAdapter
import kotlinx.serialization.ExperimentalSerializationApi

class MobicaDatabase(databaseDriverFactory: DatabaseDriverFactory, context: Any) {

    @OptIn(ExperimentalSerializationApi::class)
    private val database = MobicaDatabase(
        driver = databaseDriverFactory.createDriver(context = context)!!,
        CardEntityAdapter = CardEntity.Adapter(
            attributeEntityAdapter = attributeAdapter,
            titleEntityAdapter = titleAdapter,
            descriptionEntityAdapter = descriptionAdapter,
            imageEntityAdapter = imageAdapter
        )
    )
    private val databaseQuery = database.mobicaDatabaseQueries

    internal fun clearDatabase() {
        databaseQuery.transaction {
            databaseQuery.deleteAllCards()
        }
    }

    internal fun saveCardList(cardList: List<CardEntity>): List<CardEntity> {
        cardList.forEach { cardEntity ->
            databaseQuery.insertCard(
                cardType = cardEntity.cardType,
                value = cardEntity.value,
                attributeEntity = cardEntity.attributeEntity,
                titleEntity = cardEntity.titleEntity,
                descriptionEntity = cardEntity.descriptionEntity,
                imageEntity = cardEntity.imageEntity
            )
        }
        return databaseQuery.getAllCards().executeAsList()
    }

    internal fun loadCardList(): List<CardEntity> {
        return databaseQuery.getAllCards().executeAsList()
    }

    internal fun loadCardDetailsById(cardId: Long): CardEntity {
        return databaseQuery.getCardDetailsById(id = cardId).executeAsOne()
    }
}