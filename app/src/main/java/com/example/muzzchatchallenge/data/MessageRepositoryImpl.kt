package com.example.muzzchatchallenge.data

import com.example.muzzchatchallenge.domain.model.Message
import com.example.muzzchatchallenge.domain.repository.MessageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MessageRepositoryImpl @Inject constructor(private val chatDao: ChatDao) : MessageRepository {

    override suspend fun saveMessages(message: Message) {
        chatDao.saveMessage(message.convert())
    }

    override fun getAllMessages(): Flow<List<Message>> {
        return chatDao.getAllMessages().map { list ->
            list.map {
                it.convert()
            }
        }
    }

    override suspend fun getLastMessage(): Message {
        return chatDao.getLastMessage()?.convert() ?: chatDao.getLastMessage().convertNullable()
    }

    override suspend fun deleteMessages() {
        chatDao.deleteMessages()
    }

    override suspend fun updateMessage(int: Int, hasTail: Boolean) {
        chatDao.updateMessageTail(int, hasTail)
    }

    override suspend fun updateItemSection(id: Int, requireSection: Boolean) {
        chatDao.updateItemSection(id, requireSection)
    }
}