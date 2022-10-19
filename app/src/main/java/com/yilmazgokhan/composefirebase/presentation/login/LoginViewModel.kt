package com.yilmazgokhan.composefirebase.presentation.login

import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.google.firebase.auth.AuthCredential
import com.yilmazgokhan.composefirebase.AuthenticationState
import com.yilmazgokhan.composefirebase.base.BaseViewModel
import com.yilmazgokhan.composefirebase.base.IViewEvent
import com.yilmazgokhan.composefirebase.base.IViewState
import com.yilmazgokhan.composefirebase.domain.FirebaseAuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firebaseAuthUseCase: FirebaseAuthUseCase
) : BaseViewModel<LoginViewState, LoginViewEvent>() {

    fun loginWithCredential(authCredential: AuthCredential) {
        viewModelScope.launch {
            firebaseAuthUseCase.invoke(authCredential).collect {
                when (it.authenticationState) {
                    AuthenticationState.AUTHENTICATED -> {
                        LoginViewEvent.SetLoginState(it.authenticationState)
                    }

                    AuthenticationState.UNAUTHENTICATED -> {
                        LoginViewEvent.SetLoginState(it.authenticationState)

                    }

                    AuthenticationState.IN_PROGRESS -> {
                        LoginViewEvent.SetLoginState(it.authenticationState)

                    }
                }
            }
        }
    }

    init {
        LogUtils.d("$this")
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