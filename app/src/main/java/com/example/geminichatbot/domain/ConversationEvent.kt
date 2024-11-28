package com.example.geminichatbot.domain

sealed class ConversationEvent {
    data class SendButtonClick(val modelInput: ModelInput) : ConversationEvent()
}




data class ModelMetaData (
    val inputString: String
)