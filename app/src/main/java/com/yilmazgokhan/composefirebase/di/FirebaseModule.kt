package com.yilmazgokhan.composefirebase.di

import com.google.firebase.auth.FirebaseAuth
import com.yilmazgokhan.composefirebase.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Singleton
    @Provides
    fun provideAuthService(firebaseAuth: FirebaseAuth): AuthService {
        return AuthService(firebaseAuth)
    }
}