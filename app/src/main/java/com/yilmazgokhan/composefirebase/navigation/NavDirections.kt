package com.yilmazgokhan.composefirebase.navigation

sealed class NavDirections(val route: String) {
    object Login : NavDirections("login")
    object Register : NavDirections("register")
    object Home : NavDirections("home")
}