package com.yilmazgokhan.composefirebase.presentation.create_chat

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.yilmazgokhan.composefirebase.ui.component.DefaultScaffold
import com.yilmazgokhan.composefirebase.ui.component.DefaultToolbar

@Composable
fun CreateChatScreen(
    viewModel: CreateChatViewModel,
    navigateToBack: () -> Unit,
) {
    val state by viewModel.uiState.collectAsState()

    DefaultScaffold(topBar = { TopBar(navigateToBack) },
        loading = state.isLoading) {
        Column() {
        }
    }
}

@Composable
private fun TopBar(endIconClick: () -> Unit) {
    DefaultToolbar(
        title = "Create Chat",
        onBackPressClick = endIconClick
    )
}