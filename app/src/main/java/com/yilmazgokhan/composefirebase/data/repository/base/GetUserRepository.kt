package com.yilmazgokhan.composefirebase.data.repository.base

import com.yilmazgokhan.composefirebase.data.repository.model.User
import com.yilmazgokhan.composefirebase.util.State

interface GetUserRepository {

    suspend fun getUserById(userId: String): State<User>
}