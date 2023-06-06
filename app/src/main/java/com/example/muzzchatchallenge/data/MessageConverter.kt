package com.example.muzzchatchallenge.data

import com.example.muzzchatchallenge.domain.model.Message
import com.example.muzzchatchallenge.presentation.convert

fun MessageEntity.convert(): Message {
    return Message(
        id = this.id,
        message = this.text,
        receiver = this.receiver,
        timeSent = this.sendTime,
        hasTail = this.hasTail,
        requireSection = this.requiresSection,
        displayDate = this.sendTime.convert()
    )
}

fun MessageEntity?.convertNullable(): Message{
    return Message(
        id = this?.id
    )
}

fun Message.convert(): MessageEntity {
    return MessageEntity(
        text = this.message,
        receiver = this.receiver,
        sendTime = this.timeSent,
        hasTail = this.hasTail,
        requiresSection = this.requireSection,
    )
}




