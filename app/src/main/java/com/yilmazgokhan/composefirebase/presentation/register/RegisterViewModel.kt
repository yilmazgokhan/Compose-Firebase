package com.yilmazgokhan.composefirebase.presentation.register

import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.yilmazgokhan.composefirebase.base.BaseViewModel
import com.yilmazgokhan.composefirebase.base.IViewEvent
import com.yilmazgokhan.composefirebase.base.IViewState
import com.yilmazgokhan.composefirebase.domain.entity.User
import com.yilmazgokhan.composefirebase.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : BaseViewModel<RegisterViewState, RegisterViewEvent>() {

    init {
        LogUtils.d("$this")
        temp()
    }

    private fun temp() {
        viewModelScope.launch {
            registerUseCase.execute(
                RegisterUseCase.Input(
                    User("asd")
                )
            )
        }
    }

    override fun createInitialState(): RegisterViewState = RegisterViewState()

    override fun onTriggerEvent(event: RegisterViewEvent) {
        viewModelScope.launch {
            when (event) {
                is RegisterViewEvent.RegisterEvent -> {
                    LogUtils.d("$this")
                }
                is RegisterViewEvent.SetAddress -> {
                    LogUtils.d("$this")
                    setState {
                        currentState.copy(
                            address = event.text
                        )
                    }
                }
                is RegisterViewEvent.SetEmail -> {
                    LogUtils.d("$this")
                    setState {
                        currentState.copy(
                            email = event.text
                        )
                    }
                }
                is RegisterViewEvent.SetName -> {
                    LogUtils.d("$this")
                    setState {
                        currentState.copy(
                            name = event.text
                        )
                    }
                }
                is RegisterViewEvent.SetPhone -> {
                    LogUtils.d("$this")
                    setState {
                        currentState.copy(
                            phone = event.text
                        )
                    }
                }
                is RegisterViewEvent.SetPassword -> {
                    LogUtils.d("$this")
                    setState {
                        currentState.copy(
                            password = event.text
                        )
                    }
                }
                is RegisterViewEvent.SetPasswordConfirm -> {
                    LogUtils.d("$this")
                    setState {
                        currentState.copy(
                            passwordConfirm = event.text
                        )
                    }
                }
            }
        }
    }
}

sealed class RegisterViewEvent : IViewEvent {
    class SetName(val text: String) : RegisterViewEvent()
    class SetPhone(val text: String) : RegisterViewEvent()
    class SetEmail(val text: String) : RegisterViewEvent()
    class SetAddress(val text: String) : RegisterViewEvent()
    class SetPassword(val text: String) : RegisterViewEvent()
    class SetPasswordConfirm(val text: String) : RegisterViewEvent()
    object RegisterEvent : RegisterViewEvent()
}

data class RegisterViewState(
    val userId: Int? = null,
    val name: String = "",
    val phone: String = "",
    val email: String = "",
    val address: String = "",
    val password: String = "",
    val passwordConfirm: String = "",
    val isDisplay: Boolean = false,
    val isLoading: Boolean = false,
) : IViewState