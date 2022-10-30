package com.yilmazgokhan.composefirebase.data.datasource.impl

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.yilmazgokhan.composefirebase.data.datasource.base.LoginDataSource
import com.yilmazgokhan.composefirebase.util.State
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LoginDataSourceImpl @Inject constructor() : LoginDataSource {

    override suspend fun loginWithCredential(authCredential: AuthCredential): State<FirebaseUser> {
        return try {
            val firebaseAuthInstance = FirebaseAuth.getInstance()
            firebaseAuthInstance.signInWithCredential(authCredential).await()
            //return firebaseAuthInstance.currentUser ?: throw FirebaseAuthException("", "")
            State.Success(firebaseAuthInstance.currentUser!!)
        } catch (exception: Exception) {
            State.Error(exception)
        }
    }
}