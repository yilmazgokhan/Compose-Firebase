package com.yilmazgokhan.composefirebase.di

import com.google.firebase.firestore.FirebaseFirestore
import com.yilmazgokhan.composefirebase.data.datasource.base.ChatDataSource
import com.yilmazgokhan.composefirebase.data.datasource.base.GetUserDataSource
import com.yilmazgokhan.composefirebase.data.datasource.base.LoginDataSource
import com.yilmazgokhan.composefirebase.data.datasource.base.RegisterDataSource
import com.yilmazgokhan.composefirebase.data.datasource.impl.ChatDataSourceImpl
import com.yilmazgokhan.composefirebase.data.datasource.impl.GetUserDataSourceImpl
import com.yilmazgokhan.composefirebase.data.datasource.impl.LoginDataSourceImpl
import com.yilmazgokhan.composefirebase.data.datasource.impl.RegisterDataSourceImpl
import com.yilmazgokhan.composefirebase.data.repository.base.ChatRepository
import com.yilmazgokhan.composefirebase.data.repository.base.GetUserRepository
import com.yilmazgokhan.composefirebase.data.repository.base.LoginRepository
import com.yilmazgokhan.composefirebase.data.repository.base.RegisterRepository
import com.yilmazgokhan.composefirebase.data.repository.impl.ChatRepositoryImpl
import com.yilmazgokhan.composefirebase.data.repository.impl.GetUserRepositoryImpl
import com.yilmazgokhan.composefirebase.data.repository.impl.LoginRepositoryImpl
import com.yilmazgokhan.composefirebase.data.repository.impl.RegisterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun provideLoginDataSource(
    ): LoginDataSource =
        LoginDataSourceImpl()

    @Provides
    fun provideLoginRepository(
        loginDataSource: LoginDataSource,
    ): LoginRepository =
        LoginRepositoryImpl(loginDataSource)

    @Provides
    fun provideRegisterDataSource(
        firebaseFirestore: FirebaseFirestore,
    ): RegisterDataSource =
        RegisterDataSourceImpl(firebaseFirestore)

    @Provides
    fun provideRegisterRepository(
        registerDataSource: RegisterDataSource,
    ): RegisterRepository =
        RegisterRepositoryImpl(registerDataSource)

    @Provides
    fun provideGetUserDataSource(
        firebaseFirestore: FirebaseFirestore,
    ): GetUserDataSource =
        GetUserDataSourceImpl(firebaseFirestore)

    @Provides
    fun provideGetUserRepository(
        getUserDataSource: GetUserDataSource,
    ): GetUserRepository =
        GetUserRepositoryImpl(getUserDataSource)

    @Provides
    fun provideChatDataSource(
        firebaseFirestore: FirebaseFirestore,
    ): ChatDataSource =
        ChatDataSourceImpl(firebaseFirestore)

    @Provides
    fun provideChatRepository(
        chatDataSource: ChatDataSource,
    ): ChatRepository =
        ChatRepositoryImpl(chatDataSource)
}