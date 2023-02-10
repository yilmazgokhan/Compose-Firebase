package com.yilmazgokhan.composefirebase.presentation.home

import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.yilmazgokhan.composefirebase.base.BaseViewModel
import com.yilmazgokhan.composefirebase.base.IViewEvent
import com.yilmazgokhan.composefirebase.base.IViewState
import com.yilmazgokhan.composefirebase.data.repository.model.Chat
import com.yilmazgokhan.composefirebase.domain.usecase.GetChatsUseCase
import com.yilmazgokhan.composefirebase.util.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getChatsUseCase: GetChatsUseCase,
) : BaseViewModel<HomeViewModel.ViewState, HomeViewModel.ViewEvent>() {

    init {
        getChats()
    }

    private fun getChats() {
        triggerEvent(ViewEvent.SetLoading(true))
        viewModelScope.launch {
            when (val result = getChatsUseCase.execute(input = null)) {
                is State.Success -> {
                    val chats = result.data
                    triggerEvent(ViewEvent.SetChats(chats))
                }
                is State.Error -> {
                    LogUtils.d("${result.exception}")
                    result.exception.message?.let {
                        triggerEvent(ViewEvent.SetGetChatsError(it))
                    }
                }
            }
        }
    }

    override fun createInitialState(): ViewState = ViewState()

    override fun triggerEvent(event: ViewEvent) {
        viewModelScope.launch {
            when (event) {
                is ViewEvent.SetLoading -> {
                    setState {
                        state.copy(
                            isLoading = event.status,
                        )
                    }
                }
                is ViewEvent.SetChats -> {
                    setState {
                        state.copy(
                            isLoading = false,
                            chats = event.chats,
                            isChatsEmpty = event.chats.isEmpty()
                        )
                    }
                }
                is ViewEvent.SetGetChatsError -> {
                    setState {
                        state.copy(
                            chatsError = event.value
                        )
                    }
                }
            }
        }
    }

    sealed class ViewEvent : IViewEvent {
        class SetLoading(val status: Boolean) : ViewEvent()
        class SetGetChatsError(val value: String) : ViewEvent()
        class SetChats(val chats: List<Chat>) : ViewEvent()
    }

    data class ViewState(
        val isLoading: Boolean = false,
        val chatsError: String = "",
        val isChatsEmpty: Boolean = false,
        val chats: List<Chat> = emptyList(),
    ) : IViewState
}