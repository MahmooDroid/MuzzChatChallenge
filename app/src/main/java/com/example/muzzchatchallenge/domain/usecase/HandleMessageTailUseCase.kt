package com.example.muzzchatchallenge.domain.usecase

import com.example.muzzchatchallenge.domain.model.Message
import com.example.muzzchatchallenge.domain.repository.MessageRepository
import javax.inject.Inject

class HandleMessageTailUseCase @Inject constructor(private val messageRepository: MessageRepository) {

    suspend fun execute(currentMessage: Message) {

        if (currentMessage.message.isNotBlank()) {

            if (messageRepository.getLastMessage().id != null) {
                messageRepository.getLastMessage().let { prevMessage ->
                    val diff = currentMessage.timeSent.minus(prevMessage.timeSent)
                    val diffInSeconds = diff.div(1000)

                    if (diffInSeconds <= 20) {
                        if (prevMessage.receiver == currentMessage.receiver) {
                            messageRepository.updateMessage(prevMessage.id!!, false)
                        }
                    }
                }
            }
        }
    }
}