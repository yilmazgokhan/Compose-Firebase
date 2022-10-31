package com.yilmazgokhan.composefirebase.data.datasource.impl

import com.google.firebase.firestore.FirebaseFirestore
import com.yilmazgokhan.composefirebase.data.datasource.base.RegisterDataSource
import com.yilmazgokhan.composefirebase.domain.entity.User
import com.yilmazgokhan.composefirebase.util.Constants.Firestore.USERS
import com.yilmazgokhan.composefirebase.util.State
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RegisterDataSourceImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
) : RegisterDataSource {

    override suspend fun register(userId: String, user: User): State<User> {
        return try {
            val userRef = firebaseFirestore.collection(USERS).document(userId)
                .set(user)
                .addOnSuccessListener { }
                .addOnFailureListener { }
            val user = User("")
            State.Success(userRef as User)
        } catch (exception: Exception) {
            State.Error(exception)
        }
    }
}