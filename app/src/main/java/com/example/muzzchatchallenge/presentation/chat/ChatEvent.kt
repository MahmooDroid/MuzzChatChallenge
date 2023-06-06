package com.example.muzzchatchallenge.presentation.chat

sealed interface ChatEvent {
    object SendMessage : ChatEvent
    data class SetMessage(val message: String) : ChatEvent
    object ClearMessages : ChatEvent
    object SwitchToReceiver : ChatEvent
    object SwitchToSender : ChatEvent
}


