package com.yilmazgokhan.composefirebase.data.datasource.base

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import com.yilmazgokhan.composefirebase.util.State

interface LoginDataSource {

    suspend fun loginWithCredential(authCredential: AuthCredential): State<FirebaseUser>
}