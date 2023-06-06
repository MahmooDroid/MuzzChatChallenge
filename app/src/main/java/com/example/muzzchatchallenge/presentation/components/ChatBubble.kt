package com.example.muzzchatchallenge.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.muzzchatchallenge.domain.model.Message

@Composable
fun ChatBubble(message: Message) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        horizontalAlignment = if (message.receiver) Alignment.Start else Alignment.End
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = if (message.receiver)
                    MaterialTheme.colorScheme.primaryContainer
                else
                    MaterialTheme.colorScheme.tertiaryContainer
            ),
            shape = if (message.receiver)
                RoundedCornerShape(
                    16.dp,
                    16.dp,
                    16.dp,
                    if (message.hasTail) 0.dp else 16.dp
                )
            else
                RoundedCornerShape(
                    16.dp,
                    16.dp,
                    if (message.hasTail) 0.dp else 16.dp,
                    16.dp
                ),

            ) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = message.message,
                fontSize = 20.sp
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun ChatBubblePreview() {
    ChatBubble(
        message = Message(
            message = "This is a message",
            receiver = true,
            timeSent = 10L,
            hasTail = true
        )
    )
}