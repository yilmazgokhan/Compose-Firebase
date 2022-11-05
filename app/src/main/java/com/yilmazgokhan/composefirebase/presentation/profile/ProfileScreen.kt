package com.yilmazgokhan.composefirebase.presentation.profile

import android.R
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.yilmazgokhan.composefirebase.ui.component.ButtonDefault
import com.yilmazgokhan.composefirebase.ui.component.DefaultScaffold
import com.yilmazgokhan.composefirebase.ui.component.ToolbarWithEndIcon


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    navigateToBack: () -> Unit,
) {
    val state by viewModel.uiState.collectAsState()

    DefaultScaffold(
        topBar = {
            ToolbarWithEndIcon(
                title = "Profile",
                onBackPressClick = navigateToBack,
                endIconRes = R.drawable.ic_delete,
                endIconClick = {}
            )
        },
        bottomBar = { ButtonDefault(text = "Save", click = {}) },
        loading = state.isLoading
    ) {
        Column(Modifier
            .padding(it)
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {
        }
    }

}