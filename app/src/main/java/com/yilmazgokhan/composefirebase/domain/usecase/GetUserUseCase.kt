package com.yilmazgokhan.composefirebase.domain.usecase

import com.yilmazgokhan.composefirebase.base.UseCase
import com.yilmazgokhan.composefirebase.data.repository.base.GetUserRepository
import com.yilmazgokhan.composefirebase.data.repository.model.User
import com.yilmazgokhan.composefirebase.domain.sdk.AuthService
import com.yilmazgokhan.composefirebase.util.State
import com.yilmazgokhan.composefirebase.util.TestException
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val authService: AuthService,
    private val getUserRepository: GetUserRepository,
) : UseCase<Nothing, User>() {

    override suspend fun invoke(input: Nothing?): State<User> {
        return try {
            authService.userId?.let {
                when (val response = getUserRepository.getUserById(it)) {
                    is State.Success -> response
                    is State.Error -> response
                }
            } ?: run {
                State.Error(TestException("User not found!"))
            }
        } catch (exception: Exception) {
            State.Error(exception)
        }
    }
}