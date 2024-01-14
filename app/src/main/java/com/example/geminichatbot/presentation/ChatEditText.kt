package com.example.geminichatbot.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.geminichatbot.R
import com.example.geminichatbot.ui.theme.PurpleChat


@Composable
fun ChatEditText(
    shouldUploadFromGallery: Boolean = false,
    shouldCaptureFromCamera: Boolean = false,
    onClick : (String) -> Unit
) {
    var prompt by remember {
        mutableStateOf("")
    }
    Row(
        modifier = Modifier
            .background(
                color = Color.LightGray, shape = RoundedCornerShape(percent = 50)
            )
            .padding(horizontal = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextField(
            value = prompt, onValueChange = { prompt = it }, colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ), placeholder = {
                Text(
                    text = stringResource(id = R.string.hint),
                    color = Color.DarkGray,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }, singleLine = false,
            modifier = Modifier.weight(1f)
                .imePadding()
                .statusBarsPadding()
                .navigationBarsPadding()
        )
        if (shouldUploadFromGallery) {
            IconButton(onClick = {

            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    tint = colorResource(id = R.color.purple_custom),
                    contentDescription = "Upload from gallery"
                )
            }
        }
        if (shouldCaptureFromCamera) {
            IconButton(onClick = {
                /*TODO*/
            }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.camera_icon),
                    tint = colorResource(id = R.color.purple_custom),
                    contentDescription = "Upload from gallery"
                )
            }
        }
        IconButton(onClick = {
             onClick.invoke(prompt)
            prompt = ""
        }) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.send_icon),
                tint = colorResource(id = R.color.purple_custom),
                contentDescription = "Send"
            )
        }
    }
}

@Preview
@Composable
private fun ViewChatEdit() {
    ChatEditText(true, true,{})
}