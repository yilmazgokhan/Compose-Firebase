package com.yilmazgokhan.composefirebase.domain.usecase

import com.yilmazgokhan.composefirebase.base.UseCase
import com.yilmazgokhan.composefirebase.data.repository.base.ChatRepository
import com.yilmazgokhan.composefirebase.data.repository.model.Chat
import com.yilmazgokhan.composefirebase.util.State
import javax.inject.Inject

class GetChatsUseCase @Inject constructor(
    private val chatRepository: ChatRepository,
) : UseCase<Nothing, List<Chat>>() {

    override suspend fun invoke(input: Nothing?): State<List<Chat>> {
        return try {
            when (val response = chatRepository.getAllChats()
            ) {
                is State.Success -> response
                is State.Error -> response
            }
        } catch (exception: Exception) {
            State.Error(exception)
        }
    }
}