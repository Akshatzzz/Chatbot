package com.example.geminichatbot.data

import com.example.geminichatbot.BuildConfig
import com.example.geminichatbot.domain.AiRepository
import com.example.geminichatbot.domain.ModelInput
import com.example.geminichatbot.domain.ModelResponse
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TextualAiContentRepository: AiRepository {
    private val generativeModel = GenerativeModel(
        modelName = "gemini-pro", apiKey = BuildConfig.apiKey
    )
    override suspend fun fetchResponseForInput(input: ModelInput): ModelResponse? {
        return (input as? TextualModelInputImpl)?.inputString?.let {
            getResponseFromModel(generativeModel,it)
        }
    }
}

private suspend fun getResponseFromModel(
    generativeModel: GenerativeModel,
    query: String,
): TextualModelResponseImpl? = withContext(
    Dispatchers.Default
) {
    return@withContext generativeModel.generateContent(query).text?.let {
        TextualModelResponseImpl(
            it
        )
    }
}