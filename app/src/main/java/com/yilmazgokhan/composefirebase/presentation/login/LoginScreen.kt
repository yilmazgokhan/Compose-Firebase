package com.yilmazgokhan.composefirebase.presentation.login

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.blankj.utilcode.util.LogUtils
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.yilmazgokhan.composefirebase.ui.component.ButtonDefault
import com.yilmazgokhan.composefirebase.ui.component.DefaultScaffold
import com.yilmazgokhan.composefirebase.util.Constants.Firebase.CLIENT_ID
import com.yilmazgokhan.composefirebase.util.NavigateTo
import com.yilmazgokhan.composefirebase.util.login.AuthenticationState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    navigateToRegister: () -> Unit,
    navigateToHome: () -> Unit,
) {
    val state by viewModel.uiState.collectAsState()

    val context = LocalContext.current
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                viewModel.loginWithCredential(credential)

            } catch (e: ApiException) {
                LogUtils.d("Google sign in failed $e")
            }
        }

    DefaultScaffold(loading = state.isLoading) {
        Column(modifier = Modifier.padding(it)) {
            ButtonDefault(text = "Google Sign In",
                click = {
                    googleLogin(context, launcher)
                }
            )
        }
    }

    if (state.loginState == AuthenticationState.AUTHENTICATED) {
        NavigateTo(navigateToHome)
    }
}

private fun googleLogin(
    context: Context,
    launcher: ManagedActivityResultLauncher<Intent, ActivityResult>,
) {
    lateinit var googleSignInClient: GoogleSignInClient
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(CLIENT_ID)
        .requestEmail()
        .build()

    googleSignInClient = GoogleSignIn.getClient(context, gso)
    val signInIntent = googleSignInClient.signInIntent
    launcher.launch(signInIntent)
}