package com.kmpstudy.di.setting

import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.kmpstudy.data.db.context
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

actual fun createSettings(): Settings {
    val delegate: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    return SharedPreferencesSettings(delegate)
}