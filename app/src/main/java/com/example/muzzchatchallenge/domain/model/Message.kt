package com.example.muzzchatchallenge.domain.model

data class Message(
    val id: Int? = null,
    val message: String = "",
    val receiver: Boolean = false,
    val timeSent: Long = 0,
    val hasTail: Boolean = true,
    val requireSection: Boolean = true,
    val displayDate: String = ""
)
