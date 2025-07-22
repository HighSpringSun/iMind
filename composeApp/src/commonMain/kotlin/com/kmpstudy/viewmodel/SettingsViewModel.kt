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

    // 添加更多设置选项
    val theme = settings["theme", "system"]
    val language = settings["language", "zh"]
    val autoSave = settings["autoSave", true]

    fun save(userName: String) {
        settings["userName"] = userName
    }

    // 添加更多保存方法
    fun saveSortOrder(sortOrder: String) {
        settings["sortOrder"] = sortOrder
    }

    fun saveTheme(theme: String) {
        settings["theme"] = theme
    }

    fun saveLanguage(language: String) {
        settings["language"] = language
    }

    fun saveAutoSave(autoSave: Boolean) {
        settings["autoSave"] = autoSave
    }

    // 批量保存设置
    fun saveAllSettings(
        userName: String? = null,
        sortOrder: String? = null,
        theme: String? = null,
        language: String? = null,
        autoSave: Boolean? = null
    ) {
        userName?.let { settings["userName"] = it }
        sortOrder?.let { settings["sortOrder"] = it }
        theme?.let { settings["theme"] = it }
        language?.let { settings["language"] = it }
        autoSave?.let { settings["autoSave"] = it }
    }

    // 重置所有设置到默认值
    fun resetToDefaults() {
        settings["userName"] = "unknown"
        settings["sortOrder"] = "editAt-desc"
        settings["theme"] = "system"
        settings["language"] = "zh"
        settings["autoSave"] = true
    }
}