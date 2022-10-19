package com.yilmazgokhan.composefirebase.presentation.splash

import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.yilmazgokhan.composefirebase.base.BaseViewModel
import com.yilmazgokhan.composefirebase.base.IViewEvent
import com.yilmazgokhan.composefirebase.base.IViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
) : BaseViewModel<SplashViewState, SplashViewEvent>() {

    init {
        LogUtils.d("$this")
    }

    override fun createInitialState(): SplashViewState = SplashViewState()

    override fun onTriggerEvent(event: SplashViewEvent) {
        viewModelScope.launch {
            when (event) {
                SplashViewEvent.SplashEvent -> {
                    LogUtils.d("$this")
                    setState {
                        currentState.copy(
                            isLoading = true
                        )
                    }
                }
            }
        }
    }

    fun temp() {
        viewModelScope.launch {
            SplashViewEvent.SplashEvent
        }
    }
}

sealed class SplashViewEvent : IViewEvent {
    object SplashEvent : SplashViewEvent()
}

data class SplashViewState(
    val isLoading: Boolean = false,
) : IViewState