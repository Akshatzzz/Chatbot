package com.example.geminichatbot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.example.geminichatbot.data.ChatContent
import com.example.geminichatbot.presentation.ChatEditText
import com.example.geminichatbot.presentation.ConversationScreen
import com.example.geminichatbot.ui.theme.GeminiChatBotTheme
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            var convoList by rememberSaveable {
                mutableStateOf(emptyList<ChatContent>())
            }
            val scope = rememberCoroutineScope()
            GeminiChatBotTheme {
                val generativeModel = GenerativeModel(
                    modelName = "gemini-pro", apiKey = BuildConfig.apiKey
                )
                ChatContentScreen(
                    onClick = {
                        convoList =  convoList + ChatContent(it)
                        scope.launch {
                            val content = getResponseFromModel(generativeModel,it) ?: return@launch
                            convoList = convoList + ChatContent(content, isReply = true)
                        }
                    },
                    conversationList = convoList
                )


            }
        }
    }
}
suspend fun getResponseFromModel(generativeModel: GenerativeModel, query: String) : String? = withContext(Dispatchers.Default){
    return@withContext generativeModel.generateContent(query).text
}
@Composable
fun ChatContentScreen(
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
    conversationList: List<ChatContent>,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .safeDrawingPadding(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Your Personal Assistant",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.DarkGray,
        )
        Spacer(modifier = Modifier.height(16.dp))
        ConversationScreen(
            modifier = Modifier.weight(1f), boolList = conversationList
        )
        Spacer(modifier = Modifier.height(8.dp))
        ChatEditText(onClick = onClick)
    }
}