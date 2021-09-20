package com.example.mobicatestapp.di

import com.example.mobicatestapp.database.DatabaseDriverFactory
import com.example.mobicatestapp.database.MobicaDatabase
import org.kodein.di.DI
import org.kodein.di.bindSingleton

internal fun databaseModule(context: Any) = DI.Module(
    name = "DatabaseModule",
    init = {
        bindSingleton {
            MobicaDatabase(
                databaseDriverFactory = DatabaseDriverFactory(),
                context = context
            )
        }
    }
)