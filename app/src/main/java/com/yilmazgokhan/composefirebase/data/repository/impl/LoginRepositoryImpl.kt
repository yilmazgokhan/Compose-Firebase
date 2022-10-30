package com.yilmazgokhan.composefirebase.data.repository.impl

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import com.yilmazgokhan.composefirebase.data.datasource.base.LoginDataSource
import com.yilmazgokhan.composefirebase.data.repository.base.LoginRepository
import com.yilmazgokhan.composefirebase.util.State
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginDataSource: LoginDataSource,
) : LoginRepository {

    // TODO: Add mapper to success branch
    override suspend fun loginWithCredential(authCredential: AuthCredential): State<FirebaseUser> {
        //return loginDataSource.loginWithCredential(authCredential)
        return try {
            when (val response = loginDataSource.loginWithCredential(authCredential)) {
                is State.Success -> response
                is State.Error -> response
            }
        } catch (e: Exception) {
            State.Error(e)
        }
    }
}