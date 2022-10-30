package com.yilmazgokhan.composefirebase.data.repository.impl

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import com.yilmazgokhan.composefirebase.data.datasource.impl.LoginDataSourceImpl
import com.yilmazgokhan.composefirebase.data.repository.base.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginDataSource: LoginDataSourceImpl
) : LoginRepository {

    override suspend fun loginWithCredential(authCredential: AuthCredential): FirebaseUser {
        return loginDataSource.loginWithCredential(authCredential)
    }
}