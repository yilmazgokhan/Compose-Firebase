package com.yilmazgokhan.composefirebase.presentation.home

import com.blankj.utilcode.util.LogUtils
import com.yilmazgokhan.composefirebase.base.BaseViewModel
import com.yilmazgokhan.composefirebase.base.IViewEvent
import com.yilmazgokhan.composefirebase.base.IViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
) : BaseViewModel<HomeViewState, HomeViewEvent>() {

    init {
        LogUtils.d("$this")
    }

    override fun createInitialState(): HomeViewState {
        TODO("Not yet implemented")
    }

    override fun onTriggerEvent(event: HomeViewEvent) {
        TODO("Not yet implemented")
    }
}

sealed class HomeViewEvent : IViewEvent {
    object HomeEvent : HomeViewEvent()
}

data class HomeViewState(
    val isLoading: Boolean = false,
) : IViewState