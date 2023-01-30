package com.yilmazgokhan.composefirebase.presentation.create_chat

import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.yilmazgokhan.composefirebase.base.BaseViewModel
import com.yilmazgokhan.composefirebase.base.IViewEvent
import com.yilmazgokhan.composefirebase.base.IViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateChatViewModel @Inject constructor(
) : BaseViewModel<CreateChatViewState, CreateChatViewEvent>() {

    init {
        LogUtils.d("$this")
    }

    override fun createInitialState(): CreateChatViewState = CreateChatViewState()

    override fun triggerEvent(event: CreateChatViewEvent) {
        viewModelScope.launch {
            when (event) {
                CreateChatViewEvent.CreateChatEvent -> {

                }
                is CreateChatViewEvent.SetDescription -> {

                }
                is CreateChatViewEvent.SetTitle -> {

                }
            }
        }
    }
}

    sealed class CreateChatViewEvent : IViewEvent {
    object CreateChatEvent : CreateChatViewEvent()
    class SetTitle(val name: String) : CreateChatViewEvent()
    class SetDescription(val username: String) : CreateChatViewEvent()
}

data class CreateChatViewState(
    val isLoading: Boolean = false,
    val title: String = "",
    val description: String = "",
) : IViewState