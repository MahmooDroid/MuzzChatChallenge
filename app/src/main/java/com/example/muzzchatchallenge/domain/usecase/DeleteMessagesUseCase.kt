package com.example.muzzchatchallenge.domain.usecase

import com.example.muzzchatchallenge.domain.repository.MessageRepository
import javax.inject.Inject

class DeleteMessagesUseCase @Inject constructor(private val messageRepository: MessageRepository) {

    suspend fun execute() {
        messageRepository.deleteMessages()
    }
}