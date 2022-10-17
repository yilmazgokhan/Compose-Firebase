package com.yilmazgokhan.composefirebase.presentation.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.google.firebase.auth.AuthCredential
import com.yilmazgokhan.composefirebase.AuthenticationState
import com.yilmazgokhan.composefirebase.FirebaseAuthenticationResult
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

    var authState = mutableStateOf<FirebaseAuthenticationResult<AuthenticationState>?>(null)
        private set

    fun loginWithCredential(authCredential: AuthCredential) {
        viewModelScope.launch {
            firebaseAuthUseCase.invoke(authCredential).collect {

                authState.value = it

            }
        }
    }

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