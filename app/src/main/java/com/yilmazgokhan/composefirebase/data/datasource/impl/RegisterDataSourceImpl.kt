package com.yilmazgokhan.composefirebase.data.datasource.impl

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.yilmazgokhan.composefirebase.data.datasource.base.RegisterDataSource
import com.yilmazgokhan.composefirebase.domain.entity.User
import javax.inject.Inject

class RegisterDataSourceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    //private val firebaseFirestore: FirebaseFirestore
) : RegisterDataSource {
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