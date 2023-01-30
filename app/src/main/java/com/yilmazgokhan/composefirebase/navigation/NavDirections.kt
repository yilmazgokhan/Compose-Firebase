package com.yilmazgokhan.composefirebase.navigation

/**
 * All navigation enums
 */
sealed class NavDirections(val route: String) {
    object Splash : NavDirections("splash")
    object Login : NavDirections("login")
    object Register : NavDirections("register")
    object Home : NavDirections("home")
    object Profile : NavDirections("profile")
    object CreateChat : NavDirections("create_chat")
}