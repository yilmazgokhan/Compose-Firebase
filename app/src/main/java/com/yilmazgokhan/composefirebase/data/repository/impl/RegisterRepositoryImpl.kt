package com.yilmazgokhan.composefirebase.data.repository.impl

import com.yilmazgokhan.composefirebase.data.datasource.base.RegisterDataSource
import com.yilmazgokhan.composefirebase.data.repository.base.RegisterRepository
import com.yilmazgokhan.composefirebase.domain.entity.User
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val registerDataSource: RegisterDataSource,
) : RegisterRepository {

    override suspend fun register(userId: String, user: User) {
        registerDataSource.register(userId, user)
    }
}