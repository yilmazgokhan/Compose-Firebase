package com.yilmazgokhan.composefirebase.di

import android.content.Context
import com.yilmazgokhan.composefirebase.ComposeFirebaseApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): ComposeFirebaseApp {
        return app as ComposeFirebaseApp
    }
}