package com.example.muzzchatchallenge.domain.usecase

import com.example.muzzchatchallenge.domain.repository.MessageRepository
import javax.inject.Inject

class ItemSectionUseCase @Inject constructor(private val messageRepository: MessageRepository) {

    suspend fun execute(): Boolean {

        if (messageRepository.getLastMessage().id != null) {

            messageRepository.getLastMessage().let { prevMessage ->
                val diff = System.currentTimeMillis() - prevMessage.timeSent
                val seconds = diff / 1000
                val minutes = seconds / 60;
                val hours = minutes / 60

                if (hours >= 1) {
                    return true
                }
            }
        } else if (messageRepository.getLastMessage().id == null) {
            return true
        }
        return false
    }
}