package com.yilmazgokhan.composefirebase.presentation.create_chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Note
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yilmazgokhan.composefirebase.ui.component.DefaultScaffold
import com.yilmazgokhan.composefirebase.ui.component.DefaultTextField
import com.yilmazgokhan.composefirebase.ui.component.DefaultToolbar

@Composable
fun CreateChatScreen(
    viewModel: CreateChatViewModel,
    navigateToBack: () -> Unit,
) {
    val state by viewModel.uiState.collectAsState()

    DefaultScaffold(topBar = { TopBar(navigateToBack) },
        loading = state.isLoading) {
        Column() {
            DefaultTextField(
                modifier = Modifier.padding(vertical = 4.dp),
                value = state.title,
                iconVector = Icons.Default.Note,
                iconText = "noteIcon",
                onValueChange = {
                    viewModel.triggerEvent(CreateChatViewEvent.SetTitle(it))
                },
                label = "Title",
                placeholder = "Title",
            )

            DefaultTextField(
                modifier = Modifier.padding(vertical = 4.dp),
                value = state.description,
                iconVector = Icons.Default.Message,
                iconText = "messageIcon",
                onValueChange = {
                    viewModel.triggerEvent(CreateChatViewEvent.SetDescription(it))
                },
                label = "Description",
                placeholder = "Description",
            )
        }
    }
}

@Composable
private fun TopBar(endIconClick: () -> Unit) {
    DefaultToolbar(
        title = "Create Chat",
        onBackPressClick = endIconClick
    )
}