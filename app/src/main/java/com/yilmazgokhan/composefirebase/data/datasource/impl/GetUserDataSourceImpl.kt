package com.yilmazgokhan.composefirebase.data.datasource.impl

import com.google.firebase.firestore.FirebaseFirestore
import com.yilmazgokhan.composefirebase.data.datasource.base.GetUserDataSource
import com.yilmazgokhan.composefirebase.data.datasource.entity.UserDTO
import com.yilmazgokhan.composefirebase.util.Constants.Firestore.USERS
import com.yilmazgokhan.composefirebase.util.State
import com.yilmazgokhan.composefirebase.util.TestException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GetUserDataSourceImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
) : GetUserDataSource {

    override suspend fun getUserById(userId: String): State<UserDTO> {
        return try {
            val userRef = firebaseFirestore.collection(USERS).document(userId).get().await()
            val data = userRef.toObject(UserDTO::class.java)
            if (data != null)
                State.Success(data)
            else
                State.Error(TestException("error"))

        } catch (exception: Exception) {
            State.Error(exception)
        }
    }
}