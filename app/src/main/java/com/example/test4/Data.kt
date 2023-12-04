package com.example.test4

data class Data(
    val id: Int,
    val image: Int,
    val owner: String,
    val lastMessage: String,
    val lastActive: String,
    val unreadMessages: Int,
    val isTyping: Boolean,
    val lastMessageType: String
)
