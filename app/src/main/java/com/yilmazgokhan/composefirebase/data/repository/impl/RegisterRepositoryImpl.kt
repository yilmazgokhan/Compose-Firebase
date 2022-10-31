package com.yilmazgokhan.composefirebase.data.repository.impl

import com.yilmazgokhan.composefirebase.data.datasource.base.RegisterDataSource
import com.yilmazgokhan.composefirebase.data.repository.base.RegisterRepository
import com.yilmazgokhan.composefirebase.domain.entity.User
import com.yilmazgokhan.composefirebase.util.State
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val registerDataSource: RegisterDataSource,
) : RegisterRepository {

    override suspend fun register(userId: String, user: User): State<User> {
        return try {
            when (val response = registerDataSource.register_2(userId, user)) {
                is State.Success -> response
                is State.Error -> response
            }
        } catch (e: Exception) {
            State.Error(e)
        }
    }
}