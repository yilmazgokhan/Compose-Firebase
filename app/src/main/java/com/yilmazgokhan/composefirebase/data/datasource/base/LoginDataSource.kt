package com.yilmazgokhan.composefirebase.data.datasource.base

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser

interface LoginDataSource {
    suspend fun loginWithCredential(authCredential: AuthCredential): FirebaseUser?
}