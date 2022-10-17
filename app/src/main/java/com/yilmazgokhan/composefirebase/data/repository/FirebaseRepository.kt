package com.yilmazgokhan.composefirebase.data.repository

import com.google.firebase.auth.AuthCredential
import com.yilmazgokhan.composefirebase.data.firebase.FirebaseAuthLoginSourceProvider
import javax.inject.Inject

class FirebaseRepository @Inject constructor(private val firebaseSocialLoginSourceProvider: FirebaseAuthLoginSourceProvider) {

    suspend fun loginWithCredential(authCredential: AuthCredential) =
        firebaseSocialLoginSourceProvider.loginWithCredential(authCredential)
}