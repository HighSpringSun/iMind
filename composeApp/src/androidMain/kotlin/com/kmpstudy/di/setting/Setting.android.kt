package com.kmpstudy.di.setting

import android.content.Context
import android.content.SharedPreferences
import com.kmpstudy.data.db.context
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

actual fun createSettings(): Settings {
    val delegate: SharedPreferences =
        context.getSharedPreferences("iMind", Context.MODE_PRIVATE)
    return SharedPreferencesSettings(delegate)
}