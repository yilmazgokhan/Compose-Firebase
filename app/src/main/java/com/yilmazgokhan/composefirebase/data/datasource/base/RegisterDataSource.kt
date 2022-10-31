package com.yilmazgokhan.composefirebase.data.datasource.base

import com.yilmazgokhan.composefirebase.domain.entity.User
import com.yilmazgokhan.composefirebase.util.State
import kotlinx.coroutines.flow.Flow

interface RegisterDataSource {
    suspend fun register(userId: String, user: User): Flow<State<Void>>
    suspend fun register_2(userId: String, user: User): State<User>
}