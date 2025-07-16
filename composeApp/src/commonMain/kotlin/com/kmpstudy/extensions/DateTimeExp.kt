package com.kmpstudy.extensions

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.toLocalDateTime
//import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant


//@OptIn(ExperimentalTime::class)
//fun now(): LocalDateTime =
//    Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

@OptIn(FormatStringsInDatetimeFormats::class)
fun LocalDateTime.format(): String {
    return this.format(LocalDateTime.Format {
        byUnicodePattern("yyyy-MM-dd HH:mm:ss")
    })
}

@OptIn(ExperimentalTime::class)
fun Instant.format(timeZone: TimeZone = TimeZone.currentSystemDefault()): String {
    return this.toLocalDateTime(timeZone).format()
}