package com.example.geminichatbot.domain

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geminichatbot.data.ChatContent
import com.example.geminichatbot.data.ChatContentType
import com.example.geminichatbot.data.TextualContentChatModelInput
import com.example.geminichatbot.data.TextualContentChatModelResponse
import com.example.geminichatbot.data.TextualModelInputImpl
import com.example.geminichatbot.data.TextualModelResponseImpl
import com.google.ai.client.generativeai.type.Content
import com.google.ai.client.generativeai.type.content
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConversationViewModel
@Inject constructor(
    val aiRepository: AiRepository,
) : ViewModel() {
    private val _conversationList = mutableStateOf(emptyList<ChatContent>())
    val conversationList: State<List<ChatContent>> = _conversationList
    private val _prompt = mutableStateOf("")
    val prompt : State<String> = _prompt

    operator fun invoke(
        conversationEvent: ConversationEvent,
    ) {
        when (conversationEvent) {
            is ConversationEvent.SendButtonClick -> {
                when (conversationEvent.modelInput) {
                    is TextualModelInputImpl -> {
                        handleSingleResponseClick(conversationEvent.modelInput, conversationEvent)
                    }
                    is TextualContentChatModelInput -> {
                        handleChatResponseClick(conversationEvent.modelInput)
                    }
                }
            }
        }
    }

    private fun handleChatResponseClick(
        modelInput: TextualContentChatModelInput,
    ) {
        _conversationList.value =
            _conversationList.value + ChatContent(modelInput.inputString) + ChatContent(
                "typing...", ChatContentType.TYPING_INDICATOR
            )
        viewModelScope.launch {
            val chatHistory = generateChatHistory(conversationList)
            val content = aiRepository.fetchResponseForInput(
                TextualContentChatModelInput(
                    chatHistory,
                    prompt.value
                )
            )
            (content as? TextualContentChatModelResponse)?.let {
                val list = _conversationList.value.toMutableList()
                list.removeAt(_conversationList.value.size - 1)
                _conversationList.value = list + ChatContent(
                    content.textualResponse,
                    chatContentType = ChatContentType.REPLY
                )
            }
        }
    }

    private fun generateChatHistory(conversationList: State<List<ChatContent>>): List<Content> {
        val contentList = mutableListOf<Content>()
        for(message in conversationList.value) {
            if(message.chatContentType == ChatContentType.REPLY) {
                contentList.add(content(role = "model") { text(message.contentString) })
            }
            if(message.chatContentType == ChatContentType.NORMAL) {
                contentList.add(content(role = "user") { text(message.contentString) })
            }
        }
        return contentList
    }

    private fun handleSingleResponseClick(
        modelInput: TextualModelInputImpl,
        conversationEvent: ConversationEvent.SendButtonClick,
    ) {
        _conversationList.value =
            _conversationList.value + ChatContent(modelInput.inputString) + ChatContent(
                "typing...", ChatContentType.TYPING_INDICATOR
            )
        viewModelScope.launch {
            val content =
                aiRepository.fetchResponseForInput(conversationEvent.modelInput)
            when (content) {
                is TextualModelResponseImpl -> {
                    val list = _conversationList.value.toMutableList()
                    list.removeAt(_conversationList.value.size - 1)
                    _conversationList.value = list + ChatContent(
                        content.textualResponse,
                        chatContentType = ChatContentType.REPLY
                    )
                }

                else -> {
                    TODO("Not yet implemented !!")
                }
            }

        }
    }

    fun onInputPromptUpdated(promptString: String) {
        _prompt.value = promptString
    }
}