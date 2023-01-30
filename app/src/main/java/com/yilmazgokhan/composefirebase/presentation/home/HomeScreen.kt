package com.yilmazgokhan.composefirebase.presentation.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import com.yilmazgokhan.composefirebase.R
import com.yilmazgokhan.composefirebase.ui.component.ButtonWithBorder
import com.yilmazgokhan.composefirebase.ui.component.DefaultScaffold
import com.yilmazgokhan.composefirebase.ui.component.ToolbarWithEndIcon
import com.yilmazgokhan.composefirebase.ui.theme.Purple200

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navigateToProfile: () -> Unit,
    navigateToCreateChat: () -> Unit,
) {
    val state by viewModel.uiState.collectAsState()

    BackHandler(onBack = {
        // TODO:
    })

    DefaultScaffold(topBar = { HomeTopBar(navigateToProfile) },
        loading = state.isLoading) {
        Column() {
            ButtonWithBorder(
                text = "Create New Chat",
                textColor = Color.White,
                borderColor = Purple200,
                backgroundColor = Purple200,
                click = { navigateToCreateChat() }
            )
        }
    }
}

@Composable
fun HomeTopBar(endIconClick: () -> Unit) {
    ToolbarWithEndIcon(
        title = "Home",
        endIconRes = R.drawable.ic_baseline_person_24,
        endIconClick = endIconClick
    )
}