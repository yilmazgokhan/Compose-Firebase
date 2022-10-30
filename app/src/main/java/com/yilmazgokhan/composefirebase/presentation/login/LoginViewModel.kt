package com.yilmazgokhan.composefirebase.presentation.login

import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.google.firebase.auth.AuthCredential
import com.yilmazgokhan.composefirebase.base.BaseViewModel
import com.yilmazgokhan.composefirebase.base.IViewEvent
import com.yilmazgokhan.composefirebase.base.IViewState
import com.yilmazgokhan.composefirebase.domain.usecase.LoginUseCase
import com.yilmazgokhan.composefirebase.util.State
import com.yilmazgokhan.composefirebase.util.login.AuthenticationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : BaseViewModel<LoginViewState, LoginViewEvent>() {

    init {
        LogUtils.d("$this")
    }

    fun loginWithCredential(authCredential: AuthCredential) {
        setState { currentState.copy(isLoading = true) }
        viewModelScope.launch {
            when (loginUseCase.execute(LoginUseCase.Input(authCredential = authCredential))) {
                is State.Success -> {
                    setState {
                        currentState.copy(
                            loginState = AuthenticationState.AUTHENTICATED,
                            isLoading = false
                        )
                    }
                }
                is State.Error -> {
                    setState {
                        currentState.copy(
                            loginState = AuthenticationState.UNAUTHENTICATED,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    override fun createInitialState(): LoginViewState = LoginViewState()

    override fun onTriggerEvent(event: LoginViewEvent) {
        viewModelScope.launch {
            when (event) {
                is LoginViewEvent.SetLoginState -> {
                    LogUtils.d("$this")
                    setState {
                        currentState.copy(
                            loginState = event.state
                        )
                    }
                }
                is LoginViewEvent.SetLoading -> {
                    LogUtils.d("$this")
                    setState {
                        currentState.copy(
                            isLoading = event.state
                        )
                    }
                }
            }
        }
    }
}

sealed class LoginViewEvent : IViewEvent {
    class SetLoading(val state: Boolean) : LoginViewEvent()
    class SetLoginState(val state: AuthenticationState) : LoginViewEvent()
}

data class LoginViewState(
    val isLoading: Boolean = false,
    val loginState: AuthenticationState? = null,
) : IViewState