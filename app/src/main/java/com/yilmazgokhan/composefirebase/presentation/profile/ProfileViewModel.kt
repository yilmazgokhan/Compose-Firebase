package com.yilmazgokhan.composefirebase.presentation.profile

import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.yilmazgokhan.composefirebase.base.BaseViewModel
import com.yilmazgokhan.composefirebase.base.IViewEvent
import com.yilmazgokhan.composefirebase.base.IViewState
import com.yilmazgokhan.composefirebase.data.repository.model.User
import com.yilmazgokhan.composefirebase.domain.usecase.GetUserUseCase
import com.yilmazgokhan.composefirebase.domain.usecase.RegisterUseCase
import com.yilmazgokhan.composefirebase.util.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val registerUseCase: RegisterUseCase,
) : BaseViewModel<ProfileViewModel.ViewState, ProfileViewModel.ViewEvent>() {

    override fun createInitialState(): ViewState = ViewState()

    init {
        getUser()
    }

    private fun getUser() {
        triggerEvent(ViewEvent.SetLoading(true))
        viewModelScope.launch {
            when (val result = getUserUseCase.execute(input = null)) {
                is State.Success -> {
                    val user = result.data
                    triggerEvent(ViewEvent.SetUser(user))
                }
                is State.Error -> {
                    LogUtils.d("${result.exception}")
                    result.exception.message?.let {
                        triggerEvent(ViewEvent.SetGetUSerError(it))
                    }
                }
            }
        }
    }

    private fun updateUser() {
        triggerEvent(ViewEvent.SetLoading(true))
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
                    triggerEvent(ViewEvent.SetUser(response.data))
                }
                is State.Error -> {
                    LogUtils.d("${response.exception}")
                    response.exception.message?.let {
                        triggerEvent(ViewEvent.SetGetUSerError(it))
                    }
                }
            }
        }
    }

    override fun triggerEvent(event: ViewEvent) {
        viewModelScope.launch {
            when (event) {
                ViewEvent.Event -> {
                    getUser()
                }
                is ViewEvent.SetGetUSerError -> {
                    setState {
                        state.copy(
                            isLoading = false,
                            getUserError = event.value
                        )
                    }
                }
                is ViewEvent.SetLoading -> {
                    setState {
                        state.copy(
                            isLoading = event.status
                        )
                    }
                }
                is ViewEvent.SetUser -> {
                    setState {
                        state.copy(
                            isLoading = false,
                            username = event.user.username ?: "",
                            name = event.user.name ?: "",
                            phone = event.user.phone ?: "",
                            email = "",
                            address = event.user.address ?: "",
                            gender = event.user.gender ?: false
                        )
                    }
                }
                is ViewEvent.SetName -> {
                    setState {
                        state.copy(
                            name = event.name
                        )
                    }
                }
                is ViewEvent.SetAddress -> {
                    setState {
                        state.copy(
                            address = event.address
                        )
                    }
                }
                is ViewEvent.SetEmail -> {
                    setState {
                        state.copy(
                            email = event.email
                        )
                    }
                }
                is ViewEvent.SetPhone -> {
                    setState {
                        state.copy(
                            phone = event.phone
                        )
                    }
                }
                is ViewEvent.SetUsername -> {
                    setState {
                        state.copy(
                            username = event.username
                        )
                    }
                }
                ViewEvent.ApplyClick -> {
                    setState {
                        state.copy(
                            editMode = false
                        )
                    }
                    updateUser()
                }
                ViewEvent.EditClick -> {
                    setState {
                        state.copy(
                            editMode = true
                        )
                    }
                }
            }
        }
    }

    sealed class ViewEvent : IViewEvent {
        object Event : ViewEvent()
        class SetGetUSerError(val value: String) : ViewEvent()
        class SetLoading(val status: Boolean) : ViewEvent()
        class SetUser(val user: User) : ViewEvent()
        class SetName(val name: String) : ViewEvent()
        class SetUsername(val username: String) : ViewEvent()
        class SetPhone(val phone: String) : ViewEvent()
        class SetEmail(val email: String) : ViewEvent()
        class SetAddress(val address: String) : ViewEvent()
        object EditClick : ViewEvent()
        object ApplyClick : ViewEvent()
    }

    data class ViewState(
        val isLoading: Boolean = false,
        val getUserError: String = "",
        val username: String = "",
        val name: String = "",
        val phone: String = "",
        val email: String = "",
        val address: String = "",
        val termsCheck: Boolean = false,
        val newsletterCheck: Boolean = false,
        val gender: Boolean = false,
        val editMode: Boolean = false,
    ) : IViewState
}

/* todo
https://developer.android.com/codelabs/basic-android-kotlin-compose-viewmodel-and-state#6
 */
