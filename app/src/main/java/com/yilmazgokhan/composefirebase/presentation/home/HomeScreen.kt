package com.yilmazgokhan.composefirebase.presentation.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.yilmazgokhan.composefirebase.R
import com.yilmazgokhan.composefirebase.data.repository.model.Chat
import com.yilmazgokhan.composefirebase.ui.component.ButtonWithBorder
import com.yilmazgokhan.composefirebase.ui.component.DefaultScaffold
import com.yilmazgokhan.composefirebase.ui.component.ToolbarWithEndIcon
import com.yilmazgokhan.composefirebase.ui.theme.Purple200

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navigateToProfile: () -> Unit,
    navigateToCreateChat: () -> Unit,
) {
    val state by viewModel.uiState.collectAsState()

    BackHandler(onBack = {
        // TODO:
    })

    DefaultScaffold(topBar = { HomeTopBar(navigateToProfile) },
        bottomBar = {
            ButtonWithBorder(
                text = "Create New Chat",
                textColor = Color.White,
                borderColor = Purple200,
                backgroundColor = Purple200,
                click = { navigateToCreateChat() }
            )
        },
        loading = state.isLoading) {
        Column() {
            LazyColumn(modifier = Modifier.padding(horizontal = 4.dp)) {
                items(items = state.chats) {
                    RenderItem(it)
                }
            }
        }
    }
}

@Composable
private fun HomeTopBar(endIconClick: () -> Unit) {
    ToolbarWithEndIcon(
        title = "Home",
        endIconRes = R.drawable.ic_baseline_person_24,
        endIconClick = endIconClick
    )
}

@Composable
private fun RenderItem(chat: Chat) {
    Text(text = chat.title ?: "")

}