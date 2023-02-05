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
) : BaseViewModel<SplashViewModel.ViewState, SplashViewModel.ViewEvent>() {

    init {
        checkUser()
    }

    override fun createInitialState(): ViewState = ViewState()

    private fun checkUser() {
        viewModelScope.launch {
            val authState = if (authService.isUserLogin())
                AuthenticationState.AUTHENTICATED
            else AuthenticationState.UNAUTHENTICATED

            delay(2000)
            triggerEvent(ViewEvent.SetAuthState(authState))
        }
    }

    override fun triggerEvent(event: ViewEvent) {
        viewModelScope.launch {
            when (event) {
                ViewEvent.Event -> {
                    LogUtils.d("$this")
                    setState {
                        state.copy(
                            isLoading = true
                        )
                    }
                }
                is ViewEvent.SetAuthState -> {
                    setState {
                        state.copy(
                            isLoading = false,
                            authState = event.authState
                        )
                    }
                }
            }
        }
    }

    sealed class ViewEvent : IViewEvent {
        object Event : ViewEvent()
        class SetAuthState(val authState: AuthenticationState) : ViewEvent()
    }

    data class ViewState(
        val isLoading: Boolean = false,
        val authState: AuthenticationState? = null,
    ) : IViewState
}