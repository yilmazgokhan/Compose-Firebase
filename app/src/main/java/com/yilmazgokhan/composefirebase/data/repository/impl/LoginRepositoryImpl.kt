package com.yilmazgokhan.composefirebase.data.repository.impl

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import com.yilmazgokhan.composefirebase.data.datasource.base.LoginDataSource
import com.yilmazgokhan.composefirebase.data.repository.base.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginDataSource: LoginDataSource
) : LoginRepository {

    override suspend fun loginWithCredential(authCredential: AuthCredential): FirebaseUser {
        return loginDataSource.loginWithCredential(authCredential)
    }
}