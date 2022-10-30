package com.yilmazgokhan.composefirebase.data.repository.base

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser

interface LoginRepository {
    suspend fun loginWithCredential(authCredential: AuthCredential): FirebaseUser
}