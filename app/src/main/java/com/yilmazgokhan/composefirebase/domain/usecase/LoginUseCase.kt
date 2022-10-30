package com.yilmazgokhan.composefirebase.domain.usecase

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import com.yilmazgokhan.composefirebase.base.Inputs
import com.yilmazgokhan.composefirebase.base.UseCase
import com.yilmazgokhan.composefirebase.data.repository.base.LoginRepository
import com.yilmazgokhan.composefirebase.util.State

class LoginUseCase constructor(
    private val loginRepository: LoginRepository,
) : UseCase<LoginUseCase.Input, FirebaseUser>() {

    override suspend fun invoke(input: Input): State<FirebaseUser> {
        return try {
            when (val response = loginRepository.loginWithCredential(input.authCredential)) {
                is State.Success -> response
                is State.Error -> response
            }
        } catch (e: Exception) {
            State.Error(e)
        }
    }

    data class Input(
        val authCredential: AuthCredential,
    ) : Inputs
}

/*
override suspend fun invoke(input: Input): Flow<FirebaseUser> = flow {
    try {
        when (val response = loginRepository.loginWithCredential(input.authCredential)) {
            is State.Success -> emit(response.data)

            is State.Error -> response
        }
    } catch (e: Exception) {
        State.Error(e)
    }
}
*/
