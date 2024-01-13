package com.example.geminichatbot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.example.geminichatbot.presentation.ChatEditText
import com.example.geminichatbot.presentation.ConversationScreen
import com.example.geminichatbot.ui.theme.GeminiChatBotTheme
import com.example.geminichatbot.ui.theme.PurpleGrey80
import com.google.ai.client.generativeai.GenerativeModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window,false)
        setContent {
            GeminiChatBotTheme {
                val generativeModel = GenerativeModel(
                    modelName = "gemini",
                    apiKey = BuildConfig.apiKey
                )
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
                        modifier = Modifier.weight(1f),
                        boolList = listOf(
                            true,
                            false,
                            true,
                            false,
                            true,
                            false,
                            true,
                            false,
                            true,
                            false
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ChatEditText(shouldUploadFromGallery = true, shouldCaptureFromCamera = true)
                }
            }
        }
    }
}