package com.yilmazgokhan.composefirebase.domain.usecase

import com.yilmazgokhan.composefirebase.base.Inputs
import com.yilmazgokhan.composefirebase.base.UseCase
import com.yilmazgokhan.composefirebase.data.repository.base.RegisterRepository
import com.yilmazgokhan.composefirebase.domain.entity.User
import com.yilmazgokhan.composefirebase.domain.sdk.AuthService
import com.yilmazgokhan.composefirebase.util.State
import com.yilmazgokhan.composefirebase.util.TestException
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authService: AuthService,
    private val registerRepository: RegisterRepository,
) : UseCase<RegisterUseCase.Input, User>() {

    override suspend fun invoke(input: Input): State<User> {
        return try {
            authService.userId?.let {
                registerRepository.register(it, input.user)
                val user = User("asd")
                State.Success(user)
            } ?: run {
                State.Error(TestException("User not found!"))
            }
        } catch (exception: Exception) {
            State.Error(exception)
        }
    }

    data class Input(
        val user: User,
    ) : Inputs
}