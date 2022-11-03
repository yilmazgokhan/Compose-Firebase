package com.yilmazgokhan.composefirebase.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun DefaultScaffold(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    snackBarHost: @Composable (SnackbarHostState) -> Unit = {
        DefaultSnackBar(
            hostState = scaffoldState.snackbarHostState,
            status = SnackBarStatus.ERROR
        )
    },
    topBar: @Composable (() -> Unit) = {},
    bottomBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable (() -> Unit) = {},
    backgroundColor: Color = MaterialTheme.colors.background,
    contentColor: Color = contentColorFor(backgroundColor),
    loading: Boolean = false,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        snackbarHost = snackBarHost,
        topBar = topBar,
        content = content,
        bottomBar = bottomBar,
        contentColor = contentColor,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        backgroundColor = backgroundColor
    )

    Loading(visible = loading)
}