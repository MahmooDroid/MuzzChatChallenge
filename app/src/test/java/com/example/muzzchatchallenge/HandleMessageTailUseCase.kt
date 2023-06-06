package com.example.muzzchatchallenge

import com.example.muzzchatchallenge.domain.model.Message
import com.example.muzzchatchallenge.domain.repository.MessageRepository
import com.example.muzzchatchallenge.domain.usecase.HandleMessageTailUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


@RunWith(MockitoJUnitRunner::class)
class HandleMessageTailUseCaseTest {

    @Mock
    private lateinit var messageRepository: MessageRepository

    private lateinit var usecase: HandleMessageTailUseCase


    @Before
    fun setUp() {
        usecase = HandleMessageTailUseCase(messageRepository)
    }

    @Test
    fun testReceiverTailIsSavedFalseForReceiverLessThan20Seconds() = runBlocking {

        whenever(messageRepository.getLastMessage()).thenReturn(
            RECEIVER_TEST_MESSAGE_WITHIN_20_SECONDS
        )

        usecase.execute(RECEIVER_TEST_NEW_MESSAGE_WITHIN_20_SECONDS)

        verify(messageRepository, times(1)).updateMessage(1, false)

    }

    @Test
    fun testReceiverTailValueRemainsTrueAfter20Seconds() = runBlocking {

        whenever(messageRepository.getLastMessage()).thenReturn(
            RECEIVER_TEST_NEW_MESSAGE_WITHIN_20_SECONDS
        )

        usecase.execute(RECEIVER_TEST_MESSAGE_OUTSIDE_20_SECONDS)

        verify(messageRepository, times(0)).updateMessage(1, true)

    }

    @Test
    fun testSenderTailIsSetToFalseLessThan20Seconds() = runBlocking {
        whenever(messageRepository.getLastMessage()).thenReturn(
            SENDER_TEST_MESSAGE_WITHIN_20_SECONDS
        )

        usecase.execute(SENDER_TEST_MESSAGE_NEW_WITHIN_20_SECONDS)

        verify(messageRepository, times(1)).updateMessage(4, false)
    }

    @Test
    fun testSenderTailValueRemainsTrueAfter20Seconds() = runBlocking {

        whenever(messageRepository.getLastMessage()).thenReturn(
            SENDER_TEST_MESSAGE_WITHIN_20_SECONDS
        )

        usecase.execute(SENDER_TEST_MESSAGE_OUTSIDE_20_SECONDS)

        verify(messageRepository, times(0)).updateMessage(6, true)

    }

    @Test
    fun testTailSavesForAlternateUsers() = runBlocking {

        whenever(messageRepository.getLastMessage()).thenReturn(
            RECEIVER_TEST_MESSAGE_OUTSIDE_20_SECONDS
        )

        usecase.execute(SENDER_TEST_MESSAGE_WITHIN_20_SECONDS)

        verify(messageRepository, times(0)).updateMessage(1, true)
    }

    companion object {

        val RECEIVER_TEST_MESSAGE_WITHIN_20_SECONDS = Message(
            id = 1,
            message = "This is the previous message",
            receiver = true,
            timeSent = 1686068871700
        )

        val RECEIVER_TEST_NEW_MESSAGE_WITHIN_20_SECONDS = Message(
            id = 2,
            message = "This is the new message",
            receiver = true,
            timeSent = 1686068876674
        )

        val RECEIVER_TEST_MESSAGE_OUTSIDE_20_SECONDS = Message(
            id = 3,
            message = "Receiver message after 20 seconds",
            receiver = true,
            timeSent = 1686069990258
        )

        val SENDER_TEST_MESSAGE_WITHIN_20_SECONDS = Message(
            id = 4,
            message = "Sender message",
            receiver = true,
            timeSent = 1686070639996
        )

        val SENDER_TEST_MESSAGE_NEW_WITHIN_20_SECONDS = Message(
            id = 5,
            message = "Sender message test within 20 secs",
            receiver = true,
            timeSent = 1686070643170
        )

        val SENDER_TEST_MESSAGE_OUTSIDE_20_SECONDS = Message(
            id = 6,
            message = "Sender message test within 20 secs",
            receiver = true,
            timeSent = 1686070887486
        )
    }
}