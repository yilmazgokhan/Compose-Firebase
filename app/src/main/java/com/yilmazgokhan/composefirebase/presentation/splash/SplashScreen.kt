package com.yilmazgokhan.composefirebase.presentation.splash

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.yilmazgokhan.composefirebase.ui.component.Loading
import com.yilmazgokhan.composefirebase.util.NavigateTo
import com.yilmazgokhan.composefirebase.util.login.AuthenticationState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SplashScreen(
    viewModel: SplashViewModel,
    navigateToLogin: () -> Unit,
    navigateToHome: () -> Unit,
) {
    val state by viewModel.uiState.collectAsState()

    val authState = state.authState
    if (authState == AuthenticationState.AUTHENTICATED) {
        NavigateTo(navigateToHome)
    } else if (authState == AuthenticationState.UNAUTHENTICATED) {
        NavigateTo(navigateToLogin)
    }

    Loading(true)
}