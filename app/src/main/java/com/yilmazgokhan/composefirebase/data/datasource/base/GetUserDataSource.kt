package com.yilmazgokhan.composefirebase.data.datasource.base

import com.yilmazgokhan.composefirebase.data.datasource.entity.UserDTO
import com.yilmazgokhan.composefirebase.util.State

interface GetUserDataSource {

    suspend fun getUserById(userId: String): State<UserDTO>

}