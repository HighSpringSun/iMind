package com.kmpstudy.data.db


import app.cash.sqldelight.ColumnAdapter
import app.cash.sqldelight.db.SqlDriver
import com.kmpstudy.Database
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import comkmpstudy.sqldelight.Record

val db by lazy { provideDatabase(driver = DriverFactory().createDriver()) }

expect class DriverFactory() {
    fun createDriver(): SqlDriver
}

fun provideDatabase(driver: SqlDriver): Database {
    return Database(
        driver = driver,
        RecordAdapter = Record.Adapter(
            idAdapter = longToIntAdapter,
            createAtAdapter = longToInstantAdapter,
            editAtAdapter = longToInstantAdapter
        )
    )
}


private val longToIntAdapter = object : ColumnAdapter<Int, Long> {

    override fun decode(databaseValue: Long): Int {
        return databaseValue.toInt()
    }

    override fun encode(value: Int): Long {
        return value.toLong()
    }
}

@OptIn(ExperimentalTime::class)
private val longToInstantAdapter = object : ColumnAdapter<Instant, Long> {
    override fun decode(databaseValue: Long): Instant {
        return Instant.fromEpochMilliseconds(databaseValue)
    }

    override fun encode(value: Instant): Long {
        return value.toEpochMilliseconds()
    }

}
