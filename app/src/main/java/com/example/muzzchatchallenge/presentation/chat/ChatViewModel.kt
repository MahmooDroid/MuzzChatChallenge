package com.example.muzzchatchallenge.presentation.chat

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.muzzchatchallenge.domain.model.Message
import com.example.muzzchatchallenge.domain.usecase.DeleteMessagesUseCase
import com.example.muzzchatchallenge.domain.usecase.GetAllMessagesUseCase
import com.example.muzzchatchallenge.domain.usecase.HandleMessageTailUseCase
import com.example.muzzchatchallenge.domain.usecase.ItemSectionUseCase
import com.example.muzzchatchallenge.domain.usecase.SaveMessageUseCase
import com.example.muzzchatchallenge.presentation.convert
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val saveMessageUseCase: SaveMessageUseCase,
    private val getAllMessagesUseCase: GetAllMessagesUseCase,
    private val deleteMessagesUseCase: DeleteMessagesUseCase,
    private val handleMessageTailUseCase: HandleMessageTailUseCase,
    private val itemSectionUseCase: ItemSectionUseCase
) : ViewModel() {

    private val _state = mutableStateOf(ChatUiState())
    val state: State<ChatUiState> = _state

    init {
        getMessages()
    }

    fun onEvent(event: ChatEvent) {
        when (event) {
            is ChatEvent.SetMessage -> {
                _state.value = state.value.copy(
                    message = event.message
                )
            }

            ChatEvent.SendMessage -> {
                viewModelScope.launch {

                    val newMessage = Message(
                        message = state.value.message,
                        receiver = state.value.receiver,
                        timeSent = System.currentTimeMillis(),
                        hasTail = true,
                        requireSection = itemSectionUseCase.execute(),
                        displayDate = System.currentTimeMillis().convert()
                    )

                    handleMessageTailUseCase.execute(
                        newMessage
                    )

                    saveMessageUseCase.execute(
                        newMessage
                    )

                }
                _state.value = state.value.copy(
                    message = ""
                )
            }

            ChatEvent.ClearMessages -> {
                viewModelScope.launch {
                    deleteMessagesUseCase.execute()
                }
            }

            ChatEvent.SwitchToReceiver -> {
                _state.value = state.value.copy(
                    receiver = true
                )
            }

            ChatEvent.SwitchToSender -> {
                _state.value = state.value.copy(
                    receiver = false
                )
            }
        }
    }

    private fun getMessages() {
        getAllMessagesUseCase.execute().onEach {
            _state.value = state.value.copy(
                messageList = it
            )
        }.launchIn(viewModelScope)
    }
}
