package com.example.mobicatestapp.database

import com.squareup.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory constructor() {
    fun createDriver(context: Any?): SqlDriver?
}