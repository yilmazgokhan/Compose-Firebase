package com.yilmazgokhan.composefirebase.presentation.home

import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.yilmazgokhan.composefirebase.base.BaseViewModel
import com.yilmazgokhan.composefirebase.base.IViewEvent
import com.yilmazgokhan.composefirebase.base.IViewState
import com.yilmazgokhan.composefirebase.domain.usecase.RegisterUseCase
import com.yilmazgokhan.composefirebase.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : BaseViewModel<HomeViewState, HomeViewEvent>() {

    init {
        LogUtils.d("$this")
    }
    fun temp1() {
        viewModelScope.launch {
            registerUseCase.invoke().collect {
                when(it) {
                    is Resource.Loading -> {
                        LogUtils.d("$this")

                    }
                    is Resource.Success -> {
                        LogUtils.d("$this")

                    }
                    is Resource.Error -> {
                        LogUtils.d("$this")

                    }
                }
            }
        }
    }

    override fun createInitialState(): HomeViewState = HomeViewState()

    override fun onTriggerEvent(event: HomeViewEvent) {
        viewModelScope.launch {
            when(event) {
                HomeViewEvent.HomeEvent -> {

                }
            }
        }
    }
}

sealed class HomeViewEvent : IViewEvent {
    object HomeEvent : HomeViewEvent()
}

data class HomeViewState(
    val isLoading: Boolean = false,
) : IViewState