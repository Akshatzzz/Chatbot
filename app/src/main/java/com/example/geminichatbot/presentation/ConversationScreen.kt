package com.example.geminichatbot.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum

@Composable
fun ConversationScreen(modifier: Modifier = Modifier, boolList: List<Boolean>) {

        LazyColumn (
            modifier = modifier,
            reverseLayout = true
        ){
            items(
                boolList.reversed()
            ) {
                ConversationItem(
                    text = buildString {
                        LoremIpsum(10).values.forEach { string ->
                            append(string)
                        }
                    },
                    isReply = it
                )
            }
        }
}