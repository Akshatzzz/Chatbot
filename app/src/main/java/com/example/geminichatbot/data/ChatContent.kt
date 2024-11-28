package com.example.geminichatbot.data

data class ChatContent(
    val contentString: String,
    val chatContentType: ChatContentType = ChatContentType.NORMAL,
)

enum class ChatContentType {
    NORMAL,
    REPLY,
    TYPING_INDICATOR
}