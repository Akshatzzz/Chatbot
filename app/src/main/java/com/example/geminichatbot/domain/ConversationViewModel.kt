package com.example.geminichatbot.domain

import androidx.lifecycle.ViewModel

class ConversationViewModel : ViewModel() {
    operator fun invoke(
        conversationEvent: ConversationEvent
    ) {
        when(conversationEvent) {
            is ConversationEvent.SendMessage -> {

            }
        }
    }
}