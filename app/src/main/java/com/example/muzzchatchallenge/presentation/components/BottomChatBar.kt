package com.example.muzzchatchallenge.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import com.example.muzzchatchallenge.presentation.chat.ChatEvent
import com.example.muzzchatchallenge.presentation.chat.ChatViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BottomChatBar(
    viewModel: ChatViewModel
) {
    Row() {
        OutlinedTextField(
            modifier = Modifier.weight(1f),
            value = viewModel.state.value.message,
            onValueChange = {
                viewModel.onEvent(ChatEvent.SetMessage(it))
            },
            placeholder = { Text(text = "Enter message...") }
        )
        val keyboardController = LocalSoftwareKeyboardController.current
        Button(onClick = {
            viewModel.onEvent(ChatEvent.SendMessage)
            keyboardController?.hide()
        }) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = "Send"
            )
        }
    }
}

@Preview
@Composable
fun previewBar() {

}