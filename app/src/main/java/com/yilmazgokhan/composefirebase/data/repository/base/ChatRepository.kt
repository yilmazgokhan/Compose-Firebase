package com.yilmazgokhan.composefirebase.data.repository.base

import com.yilmazgokhan.composefirebase.data.repository.model.Chat
import com.yilmazgokhan.composefirebase.util.State

interface ChatRepository {

    suspend fun createChat(
        title: String,
        description: String,
        userId: String,
    ): State<Chat>

    suspend fun getChat(): State<Any>
}