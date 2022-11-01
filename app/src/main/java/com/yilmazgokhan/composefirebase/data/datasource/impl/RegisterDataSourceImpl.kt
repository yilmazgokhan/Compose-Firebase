package com.yilmazgokhan.composefirebase.data.datasource.impl

import com.google.firebase.firestore.FirebaseFirestore
import com.yilmazgokhan.composefirebase.data.datasource.base.RegisterDataSource
import com.yilmazgokhan.composefirebase.data.datasource.entity.UserDTO
import com.yilmazgokhan.composefirebase.util.Constants.Firestore.USERS
import com.yilmazgokhan.composefirebase.util.State
import com.yilmazgokhan.composefirebase.util.TestException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RegisterDataSourceImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
) : RegisterDataSource {

    override suspend fun register(
        userId: String,
        username: String?,
        name: String?,
        phone: String?,
        mail: String?,
        address: String?,
        gender: Boolean?,
    ): State<UserDTO> {
        return try {
            val user = UserDTO(
                username = username,
                name = name,
                phone = phone,
                mail = mail,
                address = address,
                gender = gender,
            )
            val userRef = firebaseFirestore.collection(USERS).document(userId).get().await()
            //val userRef = firebaseFirestore.collection(USERS).document(userId).set(user).await()

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