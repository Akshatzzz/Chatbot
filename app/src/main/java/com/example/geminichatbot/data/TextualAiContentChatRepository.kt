package com.example.geminichatbot.data

import com.example.geminichatbot.BuildConfig
import com.example.geminichatbot.domain.AiRepository
import com.example.geminichatbot.domain.ModelInput
import com.example.geminichatbot.domain.ModelResponse
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TextualAiContentChatRepository: AiRepository {
    private val generativeModel = GenerativeModel(
        modelName = "gemini-pro", apiKey = BuildConfig.apiKey
    )
    override suspend fun fetchResponseForInput(input: ModelInput): ModelResponse? = withContext(Dispatchers.IO) {
        val chatModelInput = input as? TextualContentChatModelInput ?: return@withContext null
        val chats = generativeModel.startChat(chatModelInput.chats)
        val newResponse = chats.sendMessage(chatModelInput.inputString).text ?: return@withContext null
        return@withContext TextualContentChatModelResponse(newResponse)
    }
}