package com.yilmazgokhan.composefirebase.data.datasource.base

import com.yilmazgokhan.composefirebase.data.datasource.entity.ChatDTO
import com.yilmazgokhan.composefirebase.util.State

interface ChatDataSource {

    suspend fun createChat(
        id: String,
        title: String,
        description: String,
        userId: String,
        date: Long,
    ): State<ChatDTO>

    suspend fun getChat(): State<Any>
}