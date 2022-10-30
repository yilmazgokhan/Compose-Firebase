package com.yilmazgokhan.composefirebase.domain.usecase

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuthException
import com.yilmazgokhan.composefirebase.data.repository.base.LoginRepository
import com.yilmazgokhan.composefirebase.util.login.AuthenticationState
import com.yilmazgokhan.composefirebase.util.login.FirebaseAuthenticationResult
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(authCredential: AuthCredential) = flow {
        emit(FirebaseAuthenticationResult.InProgress(AuthenticationState.IN_PROGRESS))
        try {
            loginRepository.loginWithCredential(authCredential)
            emit(FirebaseAuthenticationResult.Success(AuthenticationState.AUTHENTICATED))
        } catch (e: FirebaseAuthException) {
            emit(FirebaseAuthenticationResult.Failure(AuthenticationState.UNAUTHENTICATED, e))
        }
    }
}