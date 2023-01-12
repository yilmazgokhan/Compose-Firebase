package com.yilmazgokhan.composefirebase.presentation.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.yilmazgokhan.composefirebase.R
import com.yilmazgokhan.composefirebase.ui.component.DefaultScaffold
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
    ) {
        Column(modifier = Modifier
            .padding(4.dp)
            .padding(it)
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.name,
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "personIcon"
                    )
                },
                onValueChange = {},
                label = { Text(text = "Name") },
                placeholder = { Text(text = "Name") },
                enabled = state.editMode
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }

}