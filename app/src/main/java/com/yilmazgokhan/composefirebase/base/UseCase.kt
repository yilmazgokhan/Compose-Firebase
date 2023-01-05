package com.yilmazgokhan.composefirebase.base

import com.blankj.utilcode.util.LogUtils
import com.yilmazgokhan.composefirebase.util.State

abstract class UseCase<Input : Any?, Output : Any> {

    protected abstract suspend fun invoke(input: Input?): State<Output>

    suspend fun execute(input: Input?): State<Output> {
        LogUtils.d("$this $input")
        return invoke(input)
    }
}

interface Inputs