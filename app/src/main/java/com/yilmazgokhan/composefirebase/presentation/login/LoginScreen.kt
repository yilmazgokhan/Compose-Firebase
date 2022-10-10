package com.yilmazgokhan.composefirebase.presentation.login

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    navigateToRegister: () -> Unit,
    navigateToHome: () -> Unit,
) {
    val state by viewModel.uiState.collectAsState()

}
