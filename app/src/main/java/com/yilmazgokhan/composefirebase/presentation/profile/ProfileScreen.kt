package com.yilmazgokhan.composefirebase.presentation.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yilmazgokhan.composefirebase.R
import com.yilmazgokhan.composefirebase.ui.component.DefaultScaffold
import com.yilmazgokhan.composefirebase.ui.component.DefaultTextField
import com.yilmazgokhan.composefirebase.ui.component.ToolbarWithEndIcon

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    navigateToBack: () -> Unit,
) {
    val state by viewModel.uiState.collectAsState()

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
        loading = state.isLoading
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
        }
    }

}