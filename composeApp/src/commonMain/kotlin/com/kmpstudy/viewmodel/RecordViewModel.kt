package com.kmpstudy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.kmpstudy.data.db.db
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RecordViewModel : ViewModel() {

    // Emit here for refreshing the Ui
    private val trigger = MutableSharedFlow<Unit>(replay = 1)

    @OptIn(ExperimentalCoroutinesApi::class)
    val records = trigger.flatMapLatest { _ ->
        db.databaseQueries.getAllRecordByEditAtDESC().asFlow().mapToList(Dispatchers.IO)
            .stateIn(
                viewModelScope, SharingStarted.WhileSubscribed(), listOf()
            )
    }

    init {
        refresh()
    }


    fun refresh() {
        viewModelScope.launch {
            trigger.tryEmit(Unit)
        }
    }

}