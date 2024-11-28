package com.example.geminichatbot.domain

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geminichatbot.data.ChatContent
import com.example.geminichatbot.data.ChatContentType
import com.example.geminichatbot.data.TextualModelInputImpl
import com.example.geminichatbot.data.TextualModelResponseImpl
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
                        _conversationList.value =
                            _conversationList.value + ChatContent(conversationEvent.modelInput.inputString) + ChatContent(
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

                    else -> {
                        TODO("Not yet implemented !!")
                    }
                }
            }
        }
    }

    fun onInputPromptUpdated(promptString: String) {
        _prompt.value = promptString
    }
}