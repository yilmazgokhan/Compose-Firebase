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
) : BaseViewModel<RegisterViewModel.ViewState, RegisterViewModel.ViewEvent>() {

    init {
        LogUtils.d("$this")
    }

    fun temp() {
        viewModelScope.launch {
            when (val response = registerUseCase.execute(
                RegisterUseCase.Input(
                    username = state.username,
                    name = state.name,
                    phone = state.phone,
                    mail = state.email,
                    address = state.address,
                    gender = false,
                )
            )) {
                is State.Success -> {
                    response.data
                }
                is State.Error -> {
                    response.exception
                }
            }
        }
    }

    override fun createInitialState(): ViewState = ViewState()

    override fun triggerEvent(event: ViewEvent) {
        viewModelScope.launch {
            when (event) {
                is ViewEvent.Event -> {
                    LogUtils.d("$this")
                }
                is ViewEvent.SetAddress -> {
                    LogUtils.d("$this")
                    setState {
                        state.copy(
                            address = event.text
                        )
                    }
                }
                is ViewEvent.SetEmail -> {
                    LogUtils.d("$this")
                    setState {
                        state.copy(
                            email = event.text
                        )
                    }
                }
                is ViewEvent.SetUsername -> {
                    LogUtils.d("$this")
                    setState {
                        state.copy(
                            username = event.text
                        )
                    }
                }
                is ViewEvent.SetName -> {
                    LogUtils.d("$this")
                    setState {
                        state.copy(
                            name = event.text
                        )
                    }
                }
                is ViewEvent.SetPhone -> {
                    LogUtils.d("$this")
                    setState {
                        state.copy(
                            phone = event.text
                        )
                    }
                }
                is ViewEvent.SetNewsletterCheck -> {
                    LogUtils.d("$this")
                    setState {
                        state.copy(
                            newsletterCheck = event.status
                        )
                    }
                }
                is ViewEvent.SetTermsCheck -> {
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

    sealed class ViewEvent : IViewEvent {
        class SetUsername(val text: String) : ViewEvent()
        class SetName(val text: String) : ViewEvent()
        class SetPhone(val text: String) : ViewEvent()
        class SetEmail(val text: String) : ViewEvent()
        class SetAddress(val text: String) : ViewEvent()
        class SetTermsCheck(val status: Boolean) : ViewEvent()
        class SetNewsletterCheck(val status: Boolean) : ViewEvent()
        object Event : ViewEvent()
    }

    data class ViewState(
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
}