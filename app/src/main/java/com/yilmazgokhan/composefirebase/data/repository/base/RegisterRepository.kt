package com.yilmazgokhan.composefirebase.data.repository.base

import com.yilmazgokhan.composefirebase.domain.entity.User

interface RegisterRepository {
    suspend fun register(userId: String, user: User)
}