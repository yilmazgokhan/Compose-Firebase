package com.yilmazgokhan.composefirebase.data.repository

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import com.yilmazgokhan.composefirebase.data.remote.source.LoginDataSourceImpl
import com.yilmazgokhan.composefirebase.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val loginSourceImpl: LoginDataSourceImpl) :
    LoginRepository {

    override suspend fun loginWithCredential(authCredential: AuthCredential): FirebaseUser {
        return loginSourceImpl.loginWithCredential(authCredential)
    }
}