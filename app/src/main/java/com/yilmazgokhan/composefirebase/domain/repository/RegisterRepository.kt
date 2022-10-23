package com.yilmazgokhan.composefirebase.domain.repository

import com.yilmazgokhan.composefirebase.domain.entity.User

interface RegisterRepository {
    suspend fun register(user: User)
}