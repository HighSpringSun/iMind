package com.kmpstudy.di


import com.kmpstudy.viewmodel.MessageViewModel
import com.kmpstudy.viewmodel.PlusButtonViewModel
import com.kmpstudy.viewmodel.RecordViewModel
import org.kodein.di.DI
import org.kodein.di.bindProvider


val di = DI {
    bindProvider {
        MessageViewModel()
    }
    bindProvider {
        PlusButtonViewModel()
    }
    bindProvider {
        RecordViewModel()
    }
}