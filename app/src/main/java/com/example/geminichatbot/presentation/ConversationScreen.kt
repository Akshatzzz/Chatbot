package com.example.geminichatbot.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.geminichatbot.data.ChatContent

@Composable
fun ConversationScreen(modifier: Modifier = Modifier, boolList: List<ChatContent>) {
    LazyColumn(
        modifier = modifier,
        reverseLayout = true
    ) {
        items(
            boolList.reversed()
        ) {
            ConversationItem(
                text = it.contentString,
                chatContentType = it.chatContentType
            )
        }
    }
}