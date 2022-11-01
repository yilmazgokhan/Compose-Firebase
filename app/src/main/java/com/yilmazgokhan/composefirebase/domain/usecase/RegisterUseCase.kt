package com.yilmazgokhan.composefirebase.domain.usecase

import com.yilmazgokhan.composefirebase.base.Inputs
import com.yilmazgokhan.composefirebase.base.UseCase
import com.yilmazgokhan.composefirebase.data.repository.base.RegisterRepository
import com.yilmazgokhan.composefirebase.data.repository.model.User
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
                when (val response = registerRepository.register(
                    userId = it,
                    username = input.username,
                    name = input.name,
                    phone = input.phone,
                    mail = input.mail,
                    address = input.address,
                    gender = input.gender
                )) {
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

    data class Input(
        val username: String? = null,
        val name: String? = null,
        val phone: String? = null,
        val mail: String? = null,
        val address: String? = null,
        val gender: Boolean? = null,
    ) : Inputs
}