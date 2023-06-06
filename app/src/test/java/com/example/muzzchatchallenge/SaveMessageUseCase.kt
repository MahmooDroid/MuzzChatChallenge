package com.example.muzzchatchallenge

import com.example.muzzchatchallenge.domain.model.Message
import com.example.muzzchatchallenge.domain.repository.MessageRepository
import com.example.muzzchatchallenge.domain.usecase.SaveMessageUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify


@RunWith(MockitoJUnitRunner::class)
class SaveMessageUseCaseTest {


    @Mock
    private lateinit var messageRepository: MessageRepository

    private lateinit var messageUseCase: SaveMessageUseCase


    @Before
    fun setUp(){
        messageUseCase = SaveMessageUseCase(messageRepository)
    }

    @Test
    fun testMessageSuccessfullySaved() = runBlocking{

        messageUseCase.execute(TEST_MESSAGE)
        verify(messageRepository, times(1)).saveMessages(TEST_MESSAGE)
    }

    @Test
    fun testEmptyMessageSend() = runBlocking{
        messageUseCase.execute(TEST_MESSAGE_BLANK)
        verify(messageRepository, times(0)).saveMessages(TEST_MESSAGE_BLANK)
    }

    companion object{
        val TEST_MESSAGE = Message(
            message = "This is a test message"
        )

        val TEST_MESSAGE_BLANK = Message(
            message = ""
        )
    }
}