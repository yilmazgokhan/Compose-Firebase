package com.yilmazgokhan.composefirebase.data.repository.base

import com.yilmazgokhan.composefirebase.data.repository.model.User
import com.yilmazgokhan.composefirebase.util.State

interface RegisterRepository {

    suspend fun register(
        userId: String,
        username: String?,
        name: String?,
        phone: String?,
        mail: String?,
        address: String?,
        gender: Boolean?,
    ): State<User>
}