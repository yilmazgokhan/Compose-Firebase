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
) : BaseViewModel<ProfileViewState, ProfileViewEvent>() {

    override fun createInitialState(): ProfileViewState = ProfileViewState()

    init {
        getUser()
    }

    private fun getUser() {
        triggerEvent(ProfileViewEvent.SetLoading(true))
        viewModelScope.launch {
            when (val result = getUserUseCase.execute(input = null)) {
                is State.Success -> {
                    val user = result.data
                    triggerEvent(ProfileViewEvent.SetUser(user))
                }
                is State.Error -> {
                    LogUtils.d("${result.exception}")
                    result.exception.message?.let {
                        triggerEvent(ProfileViewEvent.SetGetUSerError(it))
                    }
                }
            }
        }
    }

    private fun updateUser() {
        triggerEvent(ProfileViewEvent.SetLoading(true))
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
                    triggerEvent(ProfileViewEvent.SetUser(response.data))
                }
                is State.Error -> {
                    LogUtils.d("${response.exception}")
                    response.exception.message?.let {
                        triggerEvent(ProfileViewEvent.SetGetUSerError(it))
                    }
                }
            }
        }
    }

    override fun triggerEvent(event: ProfileViewEvent) {
        viewModelScope.launch {
            when (event) {
                ProfileViewEvent.ProfileEvent -> {
                    getUser()
                }
                is ProfileViewEvent.SetGetUSerError -> {
                    setState {
                        state.copy(
                            isLoading = false,
                            getUserError = event.value
                        )
                    }
                }
                is ProfileViewEvent.SetLoading -> {
                    setState {
                        state.copy(
                            isLoading = event.status
                        )
                    }
                }
                is ProfileViewEvent.SetUser -> {
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
                is ProfileViewEvent.SetName -> {
                    setState {
                        state.copy(
                            name = event.name
                        )
                    }
                }
                is ProfileViewEvent.SetAddress -> {
                    setState {
                        state.copy(
                            address = event.address
                        )
                    }
                }
                is ProfileViewEvent.SetEmail -> {
                    setState {
                        state.copy(
                            email = event.email
                        )
                    }
                }
                is ProfileViewEvent.SetPhone -> {
                    setState {
                        state.copy(
                            phone = event.phone
                        )
                    }
                }
                is ProfileViewEvent.SetUsername -> {
                    setState {
                        state.copy(
                            username = event.username
                        )
                    }
                }
                ProfileViewEvent.ApplyClick -> {
                    setState {
                        state.copy(
                            editMode = false
                        )
                    }
                    updateUser()
                }
                ProfileViewEvent.EditClick -> {
                    setState {
                        state.copy(
                            editMode = true
                        )
                    }
                }
            }
        }
    }
}


sealed class ProfileViewEvent : IViewEvent {
    object ProfileEvent : ProfileViewEvent()
    class SetGetUSerError(val value: String) : ProfileViewEvent()
    class SetLoading(val status: Boolean) : ProfileViewEvent()
    class SetUser(val user: User) : ProfileViewEvent()
    class SetName(val name: String) : ProfileViewEvent()
    class SetUsername(val username: String) : ProfileViewEvent()
    class SetPhone(val phone: String) : ProfileViewEvent()
    class SetEmail(val email: String) : ProfileViewEvent()
    class SetAddress(val address: String) : ProfileViewEvent()
    object EditClick : ProfileViewEvent()
    object ApplyClick : ProfileViewEvent()
}

data class ProfileViewState(
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

/* todo
https://developer.android.com/codelabs/basic-android-kotlin-compose-viewmodel-and-state#6
 */
