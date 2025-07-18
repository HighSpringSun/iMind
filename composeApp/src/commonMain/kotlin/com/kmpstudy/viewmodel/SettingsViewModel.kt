package com.kmpstudy.viewmodel

import androidx.lifecycle.ViewModel
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set

class SettingsViewModel(
    private val settings: Settings
) : ViewModel() {

    val userName = settings["userName", "unknown"]

    val sortOrder = settings["sortOrder", "editAt-desc"]

    fun save(userName: String) {
        settings["userName"] = userName
    }
}