package com.yilmazgokhan.composefirebase.presentation.splash

import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.yilmazgokhan.composefirebase.base.BaseViewModel
import com.yilmazgokhan.composefirebase.base.IViewEvent
import com.yilmazgokhan.composefirebase.base.IViewState
import com.yilmazgokhan.composefirebase.domain.sdk.AuthService
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
        checkUser()
    }

    override fun createInitialState(): SplashViewState = SplashViewState()

    private fun checkUser() {
        viewModelScope.launch {
            val authState = if (authService.isUserLogin())
                AuthenticationState.AUTHENTICATED
            else AuthenticationState.UNAUTHENTICATED

            delay(2000)

            setState {
                state.copy(
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
                        state.copy(
                            isLoading = true
                        )
                    }
                }
            }
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