package com.example.geminichatbot.data

import com.example.geminichatbot.domain.ModelResponse

data class TextualModelResponseImpl(
    val textualResponse: String
): ModelResponse()