package com.example.muzzchatchallenge.presentation.chat

import com.example.muzzchatchallenge.domain.model.Message

data class ChatUiState(
    val messageList: List<Message> = emptyList(),
    val message: String = "",
    val receiver: Boolean = false
)
