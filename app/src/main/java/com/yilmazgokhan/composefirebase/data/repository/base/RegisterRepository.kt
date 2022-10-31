package com.yilmazgokhan.composefirebase.data.repository.base

import com.yilmazgokhan.composefirebase.domain.entity.User
import com.yilmazgokhan.composefirebase.util.State

interface RegisterRepository {
    suspend fun register(userId: String, user: User): State<User>
}