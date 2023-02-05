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
                is ViewEvent.SetDescription -> {
                    setState {
                        state.copy(
                            description = event.description
                        )
                    }
                }
                is ViewEvent.SetTitle -> {
                    setState {
                        state.copy(
                            title = event.title
                        )
                    }
                }
            }
        }
    }

    sealed class ViewEvent : IViewEvent {
        class SetTitle(val title: String) : ViewEvent()
        class SetDescription(val description: String) : ViewEvent()
        object CreateClicked : ViewEvent()
    }

    data class ViewState(
        val isLoading: Boolean = false,
        val title: String = "",
        val description: String = "",
    ) : IViewState
}