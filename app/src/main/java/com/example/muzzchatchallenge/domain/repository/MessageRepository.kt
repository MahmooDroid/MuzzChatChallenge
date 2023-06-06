package com.example.muzzchatchallenge.domain.repository

import com.example.muzzchatchallenge.domain.model.Message
import kotlinx.coroutines.flow.Flow

interface MessageRepository {

    suspend fun saveMessages(message: Message)

    fun getAllMessages(): Flow<List<Message>>

    suspend fun getLastMessage(): Message

    suspend fun deleteMessages()

    suspend fun updateMessage(id: Int, hasTail: Boolean)

    suspend fun updateItemSection(id: Int, requireSection: Boolean)
}