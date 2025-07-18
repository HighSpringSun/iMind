package com.kmpstudy.di


import com.kmpstudy.di.setting.createSettings
import com.kmpstudy.viewmodel.MessageViewModel
import com.kmpstudy.viewmodel.PlusButtonViewModel
import com.kmpstudy.viewmodel.RecordViewModel
import com.kmpstudy.viewmodel.SettingsViewModel
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance


val di = DI {
    bindSingleton { createSettings() }
    bindProvider { MessageViewModel() }
    bindProvider { PlusButtonViewModel() }
    bindProvider { RecordViewModel() }
    bindProvider { SettingsViewModel(instance()) }
}