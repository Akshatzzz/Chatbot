package com.example.geminichatbot.domain

sealed class ConversationEvent {
    data object SendMessage : ConversationEvent()

}