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
                is ProfileViewEvent.SetUser -> {
                }
            }
        }
    }

    private fun getUser() {
        viewModelScope.launch {
            when (val result = getUserUseCase.execute(input = null)) {
                is State.Success -> {
                    LogUtils.d("${result.data}")
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
    class SetUser(val user: User) : ProfileViewEvent()
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
    val isDisplay: Boolean = false,
    val isLoading: Boolean = false,
) : IViewState
