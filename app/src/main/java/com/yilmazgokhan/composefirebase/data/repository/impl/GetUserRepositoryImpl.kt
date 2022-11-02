package com.yilmazgokhan.composefirebase.data.repository.impl

import com.yilmazgokhan.composefirebase.data.datasource.base.GetUserDataSource
import com.yilmazgokhan.composefirebase.data.repository.base.GetUserRepository
import com.yilmazgokhan.composefirebase.data.repository.model.User
import com.yilmazgokhan.composefirebase.data.repository.model.mapModel
import com.yilmazgokhan.composefirebase.util.State
import javax.inject.Inject

class GetUserRepositoryImpl @Inject constructor(
    private val getUserDataSource: GetUserDataSource,
) : GetUserRepository {

    override suspend fun getUserById(userId: String): State<User> {
        return try {
            when (val response = getUserDataSource.getUserById(userId = userId)) {
                is State.Success -> State.Success(response.data.mapModel())
                is State.Error -> response
            }
        } catch (e: Exception) {
            State.Error(e)
        }
    }
}