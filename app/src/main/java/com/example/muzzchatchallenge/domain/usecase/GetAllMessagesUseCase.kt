package com.example.muzzchatchallenge.domain.usecase

import com.example.muzzchatchallenge.domain.model.Message
import com.example.muzzchatchallenge.domain.repository.MessageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllMessagesUseCase @Inject constructor(private val messageRepository: MessageRepository) {

    fun execute(): Flow<List<Message>> {
        return messageRepository.getAllMessages().map { messages ->
            messages.toList()
        }
    }
}