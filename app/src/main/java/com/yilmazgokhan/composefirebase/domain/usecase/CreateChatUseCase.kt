package com.yilmazgokhan.composefirebase.domain.usecase

import com.yilmazgokhan.composefirebase.base.Inputs
import com.yilmazgokhan.composefirebase.base.UseCase
import com.yilmazgokhan.composefirebase.data.repository.base.ChatRepository
import com.yilmazgokhan.composefirebase.data.repository.model.Chat
import com.yilmazgokhan.composefirebase.domain.sdk.AuthService
import com.yilmazgokhan.composefirebase.util.State
import com.yilmazgokhan.composefirebase.util.UserNotFoundException
import javax.inject.Inject

class CreateChatUseCase @Inject constructor(
    private val authService: AuthService,
    private val chatRepository: ChatRepository,
) : UseCase<CreateChatUseCase.Input, Chat>() {

    override suspend fun invoke(input: Input?): State<Chat> {
        return try {
            authService.userId?.let {
                when (val response = chatRepository.createChat(
                    title = input?.title ?: "",
                    description = input?.description ?: "",
                    userId = it
                )) {
                    is State.Success -> response
                    is State.Error -> response
                }
            } ?: run {
                State.Error(UserNotFoundException("User not found!"))
            }

        } catch (exception: Exception) {
            State.Error(exception)
        }
    }

    data class Input(
        val title: String? = null,
        val description: String? = null,
        val isAnonymous: Boolean = false,
    ) : Inputs
}