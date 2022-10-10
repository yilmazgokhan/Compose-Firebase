package com.yilmazgokhan.composefirebase.navigation

/**
 * All navigation enums
 */
sealed class NavDirections(val route: String) {
    object Login : NavDirections("login")
    object Register : NavDirections("register")
    object Home : NavDirections("home")
}