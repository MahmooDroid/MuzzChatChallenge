package com.example.muzzchatchallenge.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MessageEntity::class], version = 1)
abstract class ChatDatabase: RoomDatabase() {

    abstract fun chatDao(): ChatDao
}