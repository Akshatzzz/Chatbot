package com.example.geminichatbot.domain

interface AiRepository {
    suspend fun fetchResponseForInput(input: ModelInput) : ModelResponse?
}