package com.yilmazgokhan.composefirebase.presentation.home

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.yilmazgokhan.composefirebase.ui.component.TextSecondary

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
) {
    val state by viewModel.uiState.collectAsState()

    BackHandler(onBack = {
        // TODO:
    })

    TextSecondary(text = "Temp")
}
