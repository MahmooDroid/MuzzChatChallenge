package com.example.muzzchatchallenge.presentation.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.muzzchatchallenge.presentation.components.BottomChatBar
import com.example.muzzchatchallenge.presentation.components.ChatBubble
import com.example.muzzchatchallenge.presentation.components.ChatTopBar
import com.example.muzzchatchallenge.presentation.components.ItemSection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    viewModel: ChatViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { ChatTopBar(viewModel) },
        bottomBar = {
            BottomChatBar(viewModel)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding()
                )
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(items = viewModel.state.value.messageList, itemContent = { item ->
                    if (item.requireSection) {
                        ItemSection(timeStamp = item.displayDate)
                    }
                    ChatBubble(message = item)
                })
            }
        }
    }
}