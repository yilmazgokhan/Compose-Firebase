package com.yilmazgokhan.composefirebase.base

import kotlinx.coroutines.flow.Flow

interface UseCase<T : Any, R : Any> {
    suspend operator fun invoke(param: T): Flow<R>
}