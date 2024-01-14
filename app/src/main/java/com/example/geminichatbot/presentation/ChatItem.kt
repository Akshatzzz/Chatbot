package com.example.geminichatbot.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.example.geminichatbot.ui.theme.PurpleChat
import com.example.geminichatbot.ui.theme.PurpleChatReply

@Composable
fun ConversationItem(
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    text: String,
    isReply: Boolean = false,
) {
    val width = (LocalConfiguration.current.screenWidthDp * 0.7).dp
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = if (isReply) Alignment.Start else Alignment.End
    ) {
        Box(
            modifier = Modifier
                .background(color = if(isReply) PurpleChatReply else PurpleChat, shape = RoundedCornerShape(5))
                .padding(8.dp)
                .width(width)
        ){
            Text(
                text = text,
                style = textStyle,
                color = Color.LightGray,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}