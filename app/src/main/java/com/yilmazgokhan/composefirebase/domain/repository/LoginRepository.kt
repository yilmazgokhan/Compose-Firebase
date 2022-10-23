package com.yilmazgokhan.composefirebase.domain.repository

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser

interface LoginRepository {
    suspend fun loginWithCredential(authCredential: AuthCredential): FirebaseUser
}