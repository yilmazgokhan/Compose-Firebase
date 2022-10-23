package com.yilmazgokhan.composefirebase.data.repository

import com.yilmazgokhan.composefirebase.domain.entity.User

interface RegisterDataSource {
    suspend fun register(user: User): User
}