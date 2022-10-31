package com.yilmazgokhan.composefirebase

import com.google.firebase.auth.FirebaseAuth

class AuthService(private val firebaseAuth: FirebaseAuth) {

    fun getUserId(): String {
        return FirebaseAuth.getInstance().currentUser?.uid ?: ""
    }

    fun isUserLogin(): Boolean = firebaseAuth.currentUser != null

}