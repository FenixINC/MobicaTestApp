package com.example.mobicatestapp.database

import com.example.mobicatestapp.cache.MobicaDatabase
import com.example.mobicatestapp.constants.DatabaseConstants.DATABASE_NAME
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(context: Any?): SqlDriver {
        return NativeSqliteDriver(
            schema = MobicaDatabase.Schema,
            name = DATABASE_NAME
        )
    }
}