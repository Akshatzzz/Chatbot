package com.example.geminichatbot.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
                isReply = it.isReply
            )
        }
    }
}