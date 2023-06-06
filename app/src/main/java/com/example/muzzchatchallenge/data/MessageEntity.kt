package com.example.muzzchatchallenge.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat")
data class MessageEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "text") val text: String = "",
    @ColumnInfo(name = "receiver") val receiver: Boolean = false,
    @ColumnInfo(name = "send_time") val sendTime: Long = 0L,
    @ColumnInfo(name = "has_tail") val hasTail: Boolean = true,
    @ColumnInfo(name = "requires_section") val requiresSection: Boolean = true
)