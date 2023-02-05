package com.yilmazgokhan.composefirebase.presentation.home

import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.yilmazgokhan.composefirebase.base.BaseViewModel
import com.yilmazgokhan.composefirebase.base.IViewEvent
import com.yilmazgokhan.composefirebase.base.IViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
) : BaseViewModel<HomeViewModel.ViewState, HomeViewModel.ViewEvent>() {

    init {
        LogUtils.d("$this")
    }

    override fun createInitialState(): ViewState = ViewState()

    override fun triggerEvent(event: ViewEvent) {
        viewModelScope.launch {
            when (event) {
                ViewEvent.Event -> {

                }
            }
        }
    }

    sealed class ViewEvent : IViewEvent {
        object Event : ViewEvent()
    }

    data class ViewState(
        val isLoading: Boolean = false,
    ) : IViewState
}