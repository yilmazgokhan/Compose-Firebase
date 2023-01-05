package com.yilmazgokhan.composefirebase.domain.usecase

import com.yilmazgokhan.composefirebase.base.UseCase
import com.yilmazgokhan.composefirebase.data.repository.base.CheckUserRepository
import com.yilmazgokhan.composefirebase.util.State

class CheckUserUseCase constructor(
    private val checkUserRepository: CheckUserRepository,
) : UseCase<Nothing, Nothing>() {
    override suspend fun invoke(input: Nothing?): State<Nothing> {
        TODO("Not yet implemented")
    }
}