package com.yilmazgokhan.composefirebase.data.datasource.base

import com.yilmazgokhan.composefirebase.data.datasource.entity.UserDTO
import com.yilmazgokhan.composefirebase.util.State

interface RegisterDataSource {

    suspend fun register(
        userId: String,
        username: String?,
        name: String?,
        phone: String?,
        mail: String?,
        address: String?,
        gender: Boolean?,
    ): State<UserDTO>
}