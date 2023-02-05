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
) : BaseViewModel<CreateChatViewModel.ViewState, CreateChatViewModel.ViewEvent>() {

    init {
        LogUtils.d("$this")
    }

    override fun createInitialState(): ViewState = ViewState()

    override fun triggerEvent(event: ViewEvent) {
        viewModelScope.launch {
            when (event) {
                ViewEvent.Event -> {

                }
                is ViewEvent.SetDescription -> {

                }
                is ViewEvent.SetTitle -> {

                }
            }
        }
    }

    sealed class ViewEvent : IViewEvent {
        object Event : ViewEvent()
        class SetTitle(val name: String) : ViewEvent()
        class SetDescription(val username: String) : ViewEvent()
    }

    data class ViewState(
        val isLoading: Boolean = false,
        val title: String = "",
        val description: String = "",
    ) : IViewState
}