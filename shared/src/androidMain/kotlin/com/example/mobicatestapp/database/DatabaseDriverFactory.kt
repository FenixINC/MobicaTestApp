package com.example.mobicatestapp.database

import android.content.Context
import com.example.mobicatestapp.cache.MobicaDatabase
import com.example.mobicatestapp.constants.DatabaseConstants.DATABASE_NAME
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(context: Any?): SqlDriver? {
        return (context as? Context)?.let { context ->
            AndroidSqliteDriver(
                schema = MobicaDatabase.Schema,
                context = context,
                name = DATABASE_NAME
            )
        }
    }
}