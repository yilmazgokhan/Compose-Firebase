package com.yilmazgokhan.composefirebase.presentation.splash

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.yilmazgokhan.composefirebase.R
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

    Loader(state.isLoading)
}

@Composable
fun Loader(loading: Boolean) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash))
    val progress by animateLottieCompositionAsState(composition)
    LottieAnimation(
        composition = composition,
        progress = { progress },
    )
}