package com.example.muzzchatchallenge

import com.example.muzzchatchallenge.domain.model.Message
import com.example.muzzchatchallenge.domain.repository.MessageRepository
import com.example.muzzchatchallenge.domain.usecase.GetAllMessagesUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


@RunWith(MockitoJUnitRunner::class)
class GetAllMessagesUseCaseTest {

    @Mock
    private lateinit var messageRepository: MessageRepository


    private lateinit var getAllMessagesUseCase: GetAllMessagesUseCase


    @Before
    fun setUp() {
        getAllMessagesUseCase = GetAllMessagesUseCase(messageRepository)
    }

    @Test
    fun testEmptyList() = runBlocking {
        val emptyList = flowOf(emptyList<Message>())
        whenever(messageRepository.getAllMessages()).thenReturn(emptyList)
        val getList = getAllMessagesUseCase.execute()
        verify(messageRepository, times(1)).getAllMessages()

        Assert.assertEquals(0, getList.first().size)
    }

}
