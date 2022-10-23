package com.yilmazgokhan.composefirebase.data.remote.source

import android.util.Log
import com.blankj.utilcode.util.LogUtils
import com.google.firebase.auth.FirebaseAuth
import com.yilmazgokhan.composefirebase.data.repository.RegisterDataSource
import com.yilmazgokhan.composefirebase.domain.entity.User
import com.yilmazgokhan.composefirebase.util.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterDataSourceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    //private val firebaseFirestore: FirebaseFirestore
) : RegisterDataSource{
    override suspend fun register(user: User): User {
        firebaseAuth.currentUser?.uid?.let {
            val user = User(username = "temp")
            return user
        } ?: run {
            Log.d("TAG", "register: ")
            return User(username = "temp")
        }
    }
}