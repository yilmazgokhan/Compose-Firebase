package com.yilmazgokhan.composefirebase.presentation.splash

import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.yilmazgokhan.composefirebase.AuthService
import com.yilmazgokhan.composefirebase.base.BaseViewModel
import com.yilmazgokhan.composefirebase.base.IViewEvent
import com.yilmazgokhan.composefirebase.base.IViewState
import com.yilmazgokhan.composefirebase.util.login.AuthenticationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authService: AuthService,
) : BaseViewModel<SplashViewState, SplashViewEvent>() {

    init {
        LogUtils.d("$this")
        checkUser()
    }

    override fun createInitialState(): SplashViewState = SplashViewState()

    private fun checkUser() {
        viewModelScope.launch {
            val authState = if (authService.isUserLogin())
                AuthenticationState.AUTHENTICATED
            else AuthenticationState.UNAUTHENTICATED

            delay(4000)

            setState {
                currentState.copy(
                    isLoading = false,
                    authState = authState
                )
            }
        }
    }

    override fun onTriggerEvent(event: SplashViewEvent) {
        viewModelScope.launch {
            when (event) {
                SplashViewEvent.SplashEvent -> {
                    LogUtils.d("$this")
                    setState {
                        currentState.copy(
                            isLoading = true
                        )
                    }
                }
            }
        }
    }

    fun temp() {
        viewModelScope.launch {
            SplashViewEvent.SplashEvent
        }
    }
}

sealed class SplashViewEvent : IViewEvent {
    object SplashEvent : SplashViewEvent()
}

data class SplashViewState(
    val isLoading: Boolean = false,
    val authState: AuthenticationState? = null,
) : IViewState