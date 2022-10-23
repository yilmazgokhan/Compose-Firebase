package com.yilmazgokhan.composefirebase.data.remote.source

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import com.yilmazgokhan.composefirebase.data.repository.LoginDataSource
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LoginDataSourceImpl @Inject constructor() : LoginDataSource {
    override suspend fun loginWithCredential(authCredential: AuthCredential): FirebaseUser {
        val firebaseAuthInstance = FirebaseAuth.getInstance()
        firebaseAuthInstance.signInWithCredential(authCredential).await()
        return firebaseAuthInstance.currentUser ?: throw FirebaseAuthException("", "")
    }
}