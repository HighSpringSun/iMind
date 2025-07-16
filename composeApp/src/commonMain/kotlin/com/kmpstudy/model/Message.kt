package com.kmpstudy.model

sealed class MessageType {
    data object Info : MessageType()
    data object Success : MessageType()
    data object Warning : MessageType()
    data object Error : MessageType()
}

data class Message(
    val id: Int = 0, // 可以用于唯一标识消息，方便管理，比如关闭特定消息等操作
    val type: MessageType,
    val content: String
)
