package com.yilmazgokhan.composefirebase.data.repository.impl

import com.yilmazgokhan.composefirebase.data.datasource.base.ChatDataSource
import com.yilmazgokhan.composefirebase.data.repository.base.ChatRepository
import com.yilmazgokhan.composefirebase.data.repository.model.Chat
import com.yilmazgokhan.composefirebase.data.repository.model.mapModel
import com.yilmazgokhan.composefirebase.util.State
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val chatDatasource: ChatDataSource,
) : ChatRepository {

    override suspend fun createChat(
        title: String,
        description: String,
        userId: String,
    ): State<Chat> {
        return try {
            when (val response = chatDatasource.createChat(
                title = title,
                description = description,
                userId = userId
            )) {
                is State.Success -> State.Success(response.data.mapModel())
                is State.Error -> response
            }
        } catch (e: Exception) {
            State.Error(e)
        }
    }

    override suspend fun getChat(): State<Any> {
        TODO("Not yet implemented")
    }
}