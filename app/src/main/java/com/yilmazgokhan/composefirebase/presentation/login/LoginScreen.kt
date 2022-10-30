package com.yilmazgokhan.composefirebase.presentation.login

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
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

    DefaultScaffold(loading = state.isLoading || state.loginState == AuthenticationState.IN_PROGRESS) {
        Column(modifier = Modifier.padding(it)) {
            ButtonDefault(text = "Google Sign In",
                click = {
                    googleLogin(context, launcher)
                }
            )
        }
    }
    Log.d("xyztyzxc", "temp: ${System.currentTimeMillis()}")

    if (state.loginState == AuthenticationState.AUTHENTICATED) {
        Log.d("xyztyzxc", "LoginScreen: ${System.currentTimeMillis()}")
        navigateToHome()
    }
}

private fun googleLogin(
    context: Context,
    launcher: ManagedActivityResultLauncher<Intent, ActivityResult>
) {
    lateinit var googleSignInClient: GoogleSignInClient
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken("854786605418-68nkv5ep7n15euu0ngvumbm4map7l8pp.apps.googleusercontent.com")
        .requestEmail()
        .build()

    googleSignInClient = GoogleSignIn.getClient(context, gso)
    val signInIntent = googleSignInClient.signInIntent
    launcher.launch(signInIntent)
}