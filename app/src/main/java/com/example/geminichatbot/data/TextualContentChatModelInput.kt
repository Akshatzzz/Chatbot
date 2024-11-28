package com.example.geminichatbot.data

import com.example.geminichatbot.domain.ModelInput
import com.google.ai.client.generativeai.type.Content

data class TextualContentChatModelInput(
    val chats: List<Content> = emptyList(),
    val inputString: String
) : ModelInput()