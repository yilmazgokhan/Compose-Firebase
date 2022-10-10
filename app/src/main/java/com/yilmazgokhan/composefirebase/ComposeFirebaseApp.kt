package com.yilmazgokhan.composefirebase

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ComposeFirebaseApp : Application() {

    val isDark = mutableStateOf(false)

    fun toggleTheme() {
        isDark.value = !isDark.value
    }
}