package com.yilmazgokhan.composefirebase.presentation.login

import com.blankj.utilcode.util.LogUtils
import com.yilmazgokhan.composefirebase.base.BaseViewModel
import com.yilmazgokhan.composefirebase.base.IViewEvent
import com.yilmazgokhan.composefirebase.base.IViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
) : BaseViewModel<LoginViewState, LoginViewEvent>() {

    init {
        LogUtils.d("$this")
    }

    override fun createInitialState(): LoginViewState = LoginViewState()

    override fun onTriggerEvent(event: LoginViewEvent) {
        TODO("Not yet implemented")
    }
}

sealed class LoginViewEvent : IViewEvent {
    object LoginEvent : LoginViewEvent()
}

data class LoginViewState(
    val isLoading: Boolean = false,
) : IViewState