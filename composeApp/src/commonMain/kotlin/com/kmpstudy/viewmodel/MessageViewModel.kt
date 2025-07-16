package com.kmpstudy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.kmpstudy.model.Message

class MessageViewModel : ViewModel() {
    val messages = mutableStateListOf<Message>()
    val isShowingMessage = mutableStateOf(false)

    fun showMessage(message: Message) {
        messages.add(message)
        isShowingMessage.value = true
    }

    fun removeMessage(message: Message) {
        messages.remove(message)
        if (messages.isEmpty()) {
            isShowingMessage.value = false
        }
    }
}
