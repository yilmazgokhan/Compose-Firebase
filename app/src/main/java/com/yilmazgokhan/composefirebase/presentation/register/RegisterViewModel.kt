package com.yilmazgokhan.composefirebase.presentation.register

import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.yilmazgokhan.composefirebase.base.BaseViewModel
import com.yilmazgokhan.composefirebase.base.IViewEvent
import com.yilmazgokhan.composefirebase.base.IViewState
import com.yilmazgokhan.composefirebase.domain.usecase.RegisterUseCase
import com.yilmazgokhan.composefirebase.util.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
) : BaseViewModel<RegisterViewState, RegisterViewEvent>() {

    init {
        LogUtils.d("$this")
    }

    fun temp() {
        viewModelScope.launch {
            when (val response = registerUseCase.execute(RegisterUseCase.Input(
                username = state.username,
                name = state.name,
                phone = state.phone,
                mail = state.email,
                address = state.address,
                gender = false,
            ))) {
                is State.Success -> {
                    response.data
                }
                is State.Error -> {
                    response.exception
                }
            }
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
                        state.copy(
                            address = event.text
                        )
                    }
                }
                is RegisterViewEvent.SetEmail -> {
                    LogUtils.d("$this")
                    setState {
                        state.copy(
                            email = event.text
                        )
                    }
                }
                is RegisterViewEvent.SetUsername -> {
                    LogUtils.d("$this")
                    setState {
                        state.copy(
                            username = event.text
                        )
                    }
                }
                is RegisterViewEvent.SetName -> {
                    LogUtils.d("$this")
                    setState {
                        state.copy(
                            name = event.text
                        )
                    }
                }
                is RegisterViewEvent.SetPhone -> {
                    LogUtils.d("$this")
                    setState {
                        state.copy(
                            phone = event.text
                        )
                    }
                }
                is RegisterViewEvent.SetNewsletterCheck -> {
                    LogUtils.d("$this")
                    setState {
                        state.copy(
                            newsletterCheck = event.status
                        )
                    }
                }
                is RegisterViewEvent.SetTermsCheck -> {
                    LogUtils.d("$this")
                    setState {
                        state.copy(
                            termsCheck = event.status
                        )
                    }
                }
            }
        }
    }
}

sealed class RegisterViewEvent : IViewEvent {
    class SetUsername(val text: String) : RegisterViewEvent()
    class SetName(val text: String) : RegisterViewEvent()
    class SetPhone(val text: String) : RegisterViewEvent()
    class SetEmail(val text: String) : RegisterViewEvent()
    class SetAddress(val text: String) : RegisterViewEvent()
    class SetTermsCheck(val status: Boolean) : RegisterViewEvent()
    class SetNewsletterCheck(val status: Boolean) : RegisterViewEvent()
    object RegisterEvent : RegisterViewEvent()
}

data class RegisterViewState(
    val username: String = "",
    val name: String = "",
    val phone: String = "",
    val email: String = "",
    val address: String = "",
    val termsCheck: Boolean = false,
    val newsletterCheck: Boolean = false,
    val isDisplay: Boolean = false,
    val isLoading: Boolean = false,
) : IViewState