package com.yilmazgokhan.composefirebase.domain.usecase

import com.yilmazgokhan.composefirebase.data.repository.RegisterRepositoryImpl
import com.yilmazgokhan.composefirebase.domain.entity.User
import com.yilmazgokhan.composefirebase.util.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val registerRepository: RegisterRepositoryImpl
) {
    suspend operator fun invoke() = flow {
        emit(Resource.Loading())
        try {
            val user = User("asd")
            registerRepository.register(user)
            emit(Resource.Success(user))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: ""))
        }
    }
}