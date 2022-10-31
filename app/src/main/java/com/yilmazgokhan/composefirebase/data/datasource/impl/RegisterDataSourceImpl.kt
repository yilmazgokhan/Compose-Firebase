package com.yilmazgokhan.composefirebase.data.datasource.impl

import com.google.firebase.firestore.FirebaseFirestore
import com.yilmazgokhan.composefirebase.data.datasource.base.RegisterDataSource
import com.yilmazgokhan.composefirebase.domain.entity.User
import com.yilmazgokhan.composefirebase.util.Constants.Firestore.USERS
import com.yilmazgokhan.composefirebase.util.State
import com.yilmazgokhan.composefirebase.util.TestException
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RegisterDataSourceImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
) : RegisterDataSource {

    override suspend fun register(userId: String, user: User) = flow {
        try {
            val userRef = firebaseFirestore.collection(USERS).document(userId)
                .set(user).await()
            emit(State.Success(userRef))
        } catch (exception: Exception) {
            emit(State.Error(exception))
        }
    }

    override suspend fun register_2(userId: String, user: User): State<User> {
        return try {
            val userRef = firebaseFirestore.collection(USERS).document(userId).get().await()

            val data = userRef.toObject(User::class.java)
            if (data != null)
                State.Success(data)
            else
                State.Error(TestException("error"))

        } catch (exception: Exception) {
            State.Error(exception)
        }
    }
}