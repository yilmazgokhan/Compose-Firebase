package com.yilmazgokhan.composefirebase.presentation.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.yilmazgokhan.composefirebase.R
import com.yilmazgokhan.composefirebase.ui.component.DefaultScaffold
import com.yilmazgokhan.composefirebase.ui.component.ToolbarWithEndIcon

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navigateToProfile: () -> Unit,
) {
    val state by viewModel.uiState.collectAsState()

    BackHandler(onBack = {
        // TODO:
    })

    DefaultScaffold(topBar = { HomeTopBar(navigateToProfile) },
        loading = state.isLoading) {
        Column() {

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