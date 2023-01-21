package com.yilmazgokhan.composefirebase.presentation.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.yilmazgokhan.composefirebase.R
import com.yilmazgokhan.composefirebase.ui.component.DefaultScaffold
import com.yilmazgokhan.composefirebase.ui.component.DefaultTextField
import com.yilmazgokhan.composefirebase.ui.component.ToolbarWithEndIcon
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    navigateToBack: () -> Unit,
) {
    val state by viewModel.uiState.collectAsState()


    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = state.getUserError) {
        launch {
            scaffoldState.snackbarHostState.showSnackbar(state.getUserError)
        }
    }


    //Prepare header end icon
    val endIcon = if (state.editMode) {
        R.drawable.ic_baseline_check_24
    } else {
        R.drawable.ic_baseline_edit_24
    }

    DefaultScaffold(
        topBar = {
            ToolbarWithEndIcon(
                title = "Profile",
                onBackPressClick = navigateToBack,
                endIconRes = endIcon,
                endIconClick = {
                    if (state.editMode) {
                        viewModel.onTriggerEvent(ProfileViewEvent.ApplyClick)
                    } else {
                        viewModel.onTriggerEvent(ProfileViewEvent.EditClick)
                    }
                }
            )
        },
        loading = state.isLoading,
        scaffoldState = scaffoldState
    ) { padding ->
        Column(modifier = Modifier
            .padding(4.dp)
            .padding(padding)
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DefaultTextField(
                modifier = Modifier.padding(vertical = 4.dp),
                value = state.name,
                iconVector = Icons.Default.Person,
                iconText = "personIcon",
                onValueChange = {
                    viewModel.onTriggerEvent(ProfileViewEvent.SetName(it))
                },
                label = "Name",
                placeholder = "Name",
                enabled = state.editMode
            )

            DefaultTextField(
                modifier = Modifier.padding(vertical = 4.dp),
                value = state.username,
                iconVector = Icons.Default.Person,
                iconText = "personIcon",
                onValueChange = {
                    viewModel.onTriggerEvent(ProfileViewEvent.SetUsername(it))
                },
                label = "Username",
                placeholder = "Username",
                enabled = state.editMode
            )

            DefaultTextField(
                modifier = Modifier.padding(vertical = 4.dp),
                value = state.phone,
                iconVector = Icons.Default.Phone,
                iconText = "phoneIcon",
                onValueChange = {
                    viewModel.onTriggerEvent(ProfileViewEvent.SetPhone(it))
                },
                label = "Phone",
                placeholder = "Phone",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                enabled = state.editMode
            )

            DefaultTextField(
                modifier = Modifier.padding(vertical = 4.dp),
                value = state.email,
                iconVector = Icons.Default.Email,
                iconText = "emailIcon",
                onValueChange = {
                    viewModel.onTriggerEvent(ProfileViewEvent.SetEmail(it))
                },
                label = "Email",
                placeholder = "Email",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                enabled = state.editMode
            )

            DefaultTextField(
                modifier = Modifier.padding(vertical = 4.dp),
                value = state.address,
                iconVector = Icons.Default.Home,
                iconText = "homeIcon",
                onValueChange = {
                    viewModel.onTriggerEvent(ProfileViewEvent.SetAddress(it))
                },
                label = "Address",
                placeholder = "Address",
                enabled = state.editMode
            )
        }
    }

}