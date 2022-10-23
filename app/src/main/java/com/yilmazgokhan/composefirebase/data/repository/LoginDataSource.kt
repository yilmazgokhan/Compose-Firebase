package com.yilmazgokhan.composefirebase.data.repository

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser

interface LoginDataSource {
    suspend fun loginWithCredential(authCredential: AuthCredential): FirebaseUser?
}