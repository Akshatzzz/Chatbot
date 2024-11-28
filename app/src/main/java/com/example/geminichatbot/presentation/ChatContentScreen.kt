package com.example.geminichatbot.presentation

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.geminichatbot.data.ChatContent
import com.example.geminichatbot.data.TextualModelInputImpl
import com.example.geminichatbot.domain.ConversationEvent
import com.example.geminichatbot.domain.ConversationViewModel
import com.example.geminichatbot.domain.ModelInput

@Composable
fun ChatContentScreen(
    modifier: Modifier = Modifier,
    viewModel: ConversationViewModel = hiltViewModel()
) {
    Column(
        modifier = modifier
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
            modifier = Modifier.weight(1f), boolList = viewModel.conversationList.value
        )
        Spacer(modifier = Modifier.height(8.dp))
        ChatEditText()
    }
}