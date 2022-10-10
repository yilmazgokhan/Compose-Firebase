package com.yilmazgokhan.composefirebase.ui.component

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.unit.dp
import com.yilmazgokhan.composefirebase.R

@Composable
fun DefaultSnackBar(
    hostState: SnackbarHostState,
    status: SnackBarStatus
) {
    SnackbarHost(hostState) {
        Snackbar(
            elevation = 0.dp,
            backgroundColor = Color(integerResource(id = status.backgroundColor)),
            snackbarData = it,
            shape = MaterialTheme.shapes.medium
        )
    }
}

sealed class SnackBarStatus(val backgroundColor: Int) {
    object SUCCESS : SnackBarStatus(R.color.black)
    object ERROR : SnackBarStatus(R.color.black)
}