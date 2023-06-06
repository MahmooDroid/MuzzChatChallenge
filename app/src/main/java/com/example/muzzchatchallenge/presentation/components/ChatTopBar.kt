package com.example.muzzchatchallenge.presentation.components

import androidx.compose.foundation.background
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.muzzchatchallenge.presentation.chat.ChatEvent
import com.example.muzzchatchallenge.presentation.chat.ChatViewModel

@Composable
fun ChatTopBar(viewModel: ChatViewModel) {
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = { if (viewModel.state.value.receiver) Text(text = "Receiver") else Text(text = "Sender") },
        actions =
        {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    tint = MaterialTheme.colors.onPrimary,
                    contentDescription = "More"
                )
            }
            DropdownMenu(
                modifier = Modifier.background(MaterialTheme.colors.onSecondary),
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }) {

                DropdownMenuItem(onClick = {
                    viewModel.onEvent(ChatEvent.ClearMessages)
                    expanded = false
                }) {
                    Text(text = "Clear")
                }
                DropdownMenuItem(onClick = {
                    viewModel.onEvent(ChatEvent.SwitchToSender)
                    expanded = false
                }) {
                    Text(text = "Sender")
                }
                DropdownMenuItem(onClick = {
                    viewModel.onEvent(ChatEvent.SwitchToReceiver)
                    expanded = false
                }) {
                    Text(text = "Receiver")
                }
            }
        }
    )
}