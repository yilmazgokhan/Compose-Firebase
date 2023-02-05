package com.yilmazgokhan.composefirebase.domain.usecase

import com.yilmazgokhan.composefirebase.base.Inputs
import com.yilmazgokhan.composefirebase.base.UseCase
import com.yilmazgokhan.composefirebase.domain.sdk.AuthService
import com.yilmazgokhan.composefirebase.util.State
import javax.inject.Inject

class CreateChatUseCase @Inject constructor(
    private val authService: AuthService,
) : UseCase<CreateChatUseCase.Input, Unit>() {

    override suspend fun invoke(input: Input?): State<Unit> {
        TODO("Not yet implemented")
    }

    data class Input(
        val title: String? = null,
        val description: String? = null,
        val isAnonymous: Boolean = false
    ) : Inputs
}