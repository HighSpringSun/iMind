package com.kmpstudy.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PlusButtonViewModel : ViewModel() {
    var showPlus by mutableStateOf(true)
        private set

    fun show() {
        showPlus = true
    }

    fun hide() {
        showPlus = false
    }
}