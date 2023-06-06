package com.example.muzzchatchallenge

import com.example.muzzchatchallenge.domain.repository.MessageRepository
import com.example.muzzchatchallenge.domain.usecase.DeleteMessagesUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class DeleteMessagesUseCase {

    @Mock
    private lateinit var messageRepository: MessageRepository

    private lateinit var deleteMessagesUseCase: DeleteMessagesUseCase

    @Before
    fun setUp() {
        deleteMessagesUseCase = DeleteMessagesUseCase(messageRepository)
    }

    @Test
    fun testMessagesDeletedInvoked() = runBlocking {
        deleteMessagesUseCase.execute()
        verify(messageRepository, times(1)).deleteMessages()
    }
}
