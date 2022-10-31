package com.yilmazgokhan.composefirebase.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun NavigateTo(navigation: () -> Unit) {
    LaunchedEffect(Unit) {
        navigation()
    }
}