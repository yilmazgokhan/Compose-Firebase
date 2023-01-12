package com.yilmazgokhan.composefirebase.presentation.profile

import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.yilmazgokhan.composefirebase.base.BaseViewModel
import com.yilmazgokhan.composefirebase.base.IViewEvent
import com.yilmazgokhan.composefirebase.base.IViewState
import com.yilmazgokhan.composefirebase.data.repository.model.User
import com.yilmazgokhan.composefirebase.domain.usecase.GetUserUseCase
import com.yilmazgokhan.composefirebase.util.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
) : BaseViewModel<ProfileViewState, ProfileViewEvent>() {

    override fun createInitialState(): ProfileViewState = ProfileViewState()

    init {
        getUser()
    }

    override fun onTriggerEvent(event: ProfileViewEvent) {
        viewModelScope.launch {
            when (event) {
                ProfileViewEvent.ProfileEvent -> {
                    getUser()
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
                ProfileViewEvent.ApplyClick -> {
                    setState {
                        state.copy(
                            editMode = false
                        )
                    }
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

    private fun getUser() {
        viewModelScope.launch {
            when (val result = getUserUseCase.execute(input = null)) {
                is State.Success -> {
                    val user = result.data
                    setState {
                        state.copy(
                            isLoading = false,
                            username = user.username ?: "",
                            name = user.name ?: "",
                            phone = user.phone ?: "",
                            email = "",
                            address = user.address ?: "",
                            gender = user.gender ?: false
                        )
                    }
                    // TODO: call event 
                }
                is State.Error -> {
                    LogUtils.d("${result.exception}")
                    result.exception
                }
            }
        }
    }
}


sealed class ProfileViewEvent : IViewEvent {
    object ProfileEvent : ProfileViewEvent()
    class SetLoading(val status: Boolean) : ProfileViewEvent()
    class SetUser(val user: User) : ProfileViewEvent()
    class SetName(val name: String) : ProfileViewEvent()
    object EditClick : ProfileViewEvent()
    object ApplyClick : ProfileViewEvent()
}

data class ProfileViewState(
    val username: String = "",
    val name: String = "",
    val phone: String = "",
    val email: String = "",
    val address: String = "",
    val termsCheck: Boolean = false,
    val newsletterCheck: Boolean = false,
    val gender: Boolean = false,
    val editMode: Boolean = false,
    val isDisplay: Boolean = false,
    val isLoading: Boolean = false,
) : IViewState

/* todo
https://developer.android.com/codelabs/basic-android-kotlin-compose-viewmodel-and-state#6
 */
