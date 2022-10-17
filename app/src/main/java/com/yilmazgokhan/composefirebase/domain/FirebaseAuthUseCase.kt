package com.yilmazgokhan.composefirebase.domain

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuthException
import com.yilmazgokhan.composefirebase.AuthenticationState
import com.yilmazgokhan.composefirebase.FirebaseAuthenticationResult
import com.yilmazgokhan.composefirebase.data.repository.FirebaseRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FirebaseAuthUseCase @Inject constructor(private val firebaseRepository: FirebaseRepository) {
    suspend operator fun invoke(authCredential: AuthCredential) = flow {
        emit(FirebaseAuthenticationResult.InProgress(AuthenticationState.IN_PROGRESS))
        try {
            firebaseRepository.loginWithCredential(authCredential)?.let {
                emit(FirebaseAuthenticationResult.Success(AuthenticationState.AUTHENTICATED))
            } ?: run {
                emit(
                    FirebaseAuthenticationResult.Failure(
                        AuthenticationState.UNAUTHENTICATED,
                        null
                    )
                )
            }
        } catch (e: FirebaseAuthException) {
            emit(FirebaseAuthenticationResult.Failure(AuthenticationState.UNAUTHENTICATED, e))
        }
    }
}