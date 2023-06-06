package com.example.muzzchatchallenge

import com.example.muzzchatchallenge.domain.model.Message
import com.example.muzzchatchallenge.domain.repository.MessageRepository
import com.example.muzzchatchallenge.domain.usecase.ItemSectionUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class ItemSectionUseCaseTest {

    @Mock
    private lateinit var messageRepository: MessageRepository

    private lateinit var usecase: ItemSectionUseCase


    @Before
    fun setUp() {
        usecase = ItemSectionUseCase(messageRepository)
    }

    @Test
    fun testItemSectionReturnsTrueAfter1Hour() = runBlocking {
        whenever(messageRepository.getLastMessage()).thenReturn(
            PREVIOUS_TEST_MESSAGE
        )

        Assert.assertEquals(true, usecase.execute())
    }

    @Test
    fun testItemSectionNotRequiredWithinHour() = runBlocking {
        whenever(messageRepository.getLastMessage()).thenReturn(
            TEST_MESSAGE_WITHIN_HOUR
        )

        Assert.assertEquals(false, usecase.execute())
    }

    @Test
    fun testSectionReturnTrueWhenNoPreviousMessage() = runBlocking {
        whenever(messageRepository.getLastMessage()).thenReturn(
            NULL_MESSAGE
        )

        Assert.assertEquals(true, usecase.execute())
    }

    companion object {
        val PREVIOUS_TEST_MESSAGE = Message(
            id = 1,
            timeSent = 1686080000000
        )

        val NULL_MESSAGE = Message(
            id = null
        )

        val TEST_MESSAGE_WITHIN_HOUR = Message(
            id = 1,
            timeSent = System.currentTimeMillis()
        )
    }
}