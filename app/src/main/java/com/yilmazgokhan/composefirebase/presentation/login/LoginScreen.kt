package com.yilmazgokhan.composefirebase.presentation.login

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.yilmazgokhan.composefirebase.AuthenticationState
import com.yilmazgokhan.composefirebase.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    navigateToRegister: () -> Unit,
    navigateToHome: () -> Unit,
) {
    val state by viewModel.uiState.collectAsState()

}

private fun googleLogin(
    context: Context,
    launcher: ManagedActivityResultLauncher<Intent, ActivityResult>
) {
    lateinit var googleSignInClient: GoogleSignInClient

    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken("854786605418-peltml5envocm8j3r0ca3inpm45ngd1p.apps.googleusercontent.com")
        .requestEmail()
        .build()

    googleSignInClient = GoogleSignIn.getClient(context, gso)

    val signInIntent = googleSignInClient.signInIntent

    launcher.launch(signInIntent)

}

@Composable
fun CheckLoginState() {
    val loginState = viewModel.authState
    when (loginState.value?.authenticationState) {
        AuthenticationState.AUTHENTICATED -> {
        }

        AuthenticationState.UNAUTHENTICATED -> {
        }

        AuthenticationState.IN_PROGRESS -> {
        }
    }
}