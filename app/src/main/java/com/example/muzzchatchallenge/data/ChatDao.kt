package com.example.muzzchatchallenge.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {

    @Insert
    suspend fun saveMessage(messageEntity: MessageEntity)

    @Query("SELECT * FROM chat")
    fun getAllMessages(): Flow<List<MessageEntity>>

    @Query("SELECT * FROM chat WHERE send_time = (SELECT MAX(send_time) FROM chat)")
    suspend fun getLastMessage(): MessageEntity?

    @Query("DELETE FROM chat")
    suspend fun deleteMessages()

    @Query("UPDATE chat SET has_tail = :hasTail WHERE id = :id")
    suspend fun updateMessageTail(id: Int, hasTail: Boolean)

    @Query("UPDATE chat SET requires_section = :requiresSection WHERE id = :id")
    suspend fun updateItemSection(id: Int, requiresSection: Boolean)

}