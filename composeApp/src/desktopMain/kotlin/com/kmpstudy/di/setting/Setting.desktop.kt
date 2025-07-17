package com.kmpstudy.di.setting

import com.russhwolf.settings.PreferencesSettings
import com.russhwolf.settings.Settings
import java.util.prefs.Preferences

actual fun createSettings(): Settings {
    val delegate: Preferences = Preferences.userRoot().node("iMind")
    return PreferencesSettings(delegate)
}