package com.yilmazgokhan.composefirebase.data.datasource.base

import com.yilmazgokhan.composefirebase.domain.entity.User
import com.yilmazgokhan.composefirebase.util.State

interface RegisterDataSource {
    suspend fun register(userId: String, user: User): State<User>
}