package com.kmpstudy.data.db

import android.annotation.SuppressLint
import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.kmpstudy.Database

@SuppressLint("StaticFieldLeak")
var context: Context? = null

actual class DriverFactory {

    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(Database.Schema, context!!, "test.db")
    }
}