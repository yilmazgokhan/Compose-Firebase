package com.yilmazgokhan.composefirebase.data.repository.base

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import com.yilmazgokhan.composefirebase.util.State

interface LoginRepository {

    suspend fun loginWithCredential(authCredential: AuthCredential): State<FirebaseUser>
}