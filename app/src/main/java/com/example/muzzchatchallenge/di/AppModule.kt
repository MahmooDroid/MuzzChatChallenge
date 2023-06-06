package com.example.muzzchatchallenge.di

import android.content.Context
import androidx.room.Room
import com.example.muzzchatchallenge.data.ChatDao
import com.example.muzzchatchallenge.data.ChatDatabase
import com.example.muzzchatchallenge.data.MessageRepositoryImpl
import com.example.muzzchatchallenge.domain.repository.MessageRepository
import com.example.muzzchatchallenge.domain.usecase.DeleteMessagesUseCase
import com.example.muzzchatchallenge.domain.usecase.GetAllMessagesUseCase
import com.example.muzzchatchallenge.domain.usecase.HandleMessageTailUseCase
import com.example.muzzchatchallenge.domain.usecase.ItemSectionUseCase
import com.example.muzzchatchallenge.domain.usecase.SaveMessageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMessageDatabase(@ApplicationContext appContext: Context): ChatDatabase {
        return Room.databaseBuilder(
            context = appContext,
            klass = ChatDatabase::class.java,
            name = "chat_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideChatDao(db: ChatDatabase) = db.chatDao()


    @Provides
    @Singleton
    fun providesMessageRepository(
        messageRepositoryImpl: MessageRepositoryImpl,
        chatDao: ChatDao
    ): MessageRepository {
        return MessageRepositoryImpl(chatDao)
    }

    @Provides
    @Singleton
    fun providesSaveMessageUseCase(messageRepository: MessageRepository): SaveMessageUseCase =
        SaveMessageUseCase(messageRepository)

    @Provides
    @Singleton
    fun providesGetAllMessagesUseCase(messageRepository: MessageRepository): GetAllMessagesUseCase =
        GetAllMessagesUseCase(messageRepository)

    @Provides
    @Singleton
    fun providesDeleteMessagesUseCase(messageRepository: MessageRepository): DeleteMessagesUseCase =
        DeleteMessagesUseCase(messageRepository)


    @Provides
    @Singleton
    fun provideHandleTailUseCase(messageRepository: MessageRepository): HandleMessageTailUseCase =
        HandleMessageTailUseCase(messageRepository)

    @Provides
    @Singleton
    fun provideItemSectionUseCase(messageRepository: MessageRepository): ItemSectionUseCase =
        ItemSectionUseCase(messageRepository)
}