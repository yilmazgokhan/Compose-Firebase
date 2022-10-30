package com.yilmazgokhan.composefirebase.data.repository.impl

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.yilmazgokhan.composefirebase.domain.entity.User
import com.yilmazgokhan.composefirebase.data.repository.base.RegisterRepository
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    //private val firebaseFirestore: FirebaseFirestore
) : RegisterRepository {
    override suspend fun register(user: User) {
        FirebaseAuth.getInstance().currentUser?.uid?.let {
            Log.d("TAG", "register: ")
        } ?: run {
            Log.d("TAG", "register: ")
        }
    }
}
/*
flow {
        emit(Resource.Loading())
        firebaseAuth.currentUser?.uid?.let {
            val user = User(username = "temp")
            emit(Resource.Success(user))
        }
    }.catch {
        LogUtils.d("$this ${it.stackTrace}")
        emit(Resource.Error(it.message ?: ""))
    }
 */