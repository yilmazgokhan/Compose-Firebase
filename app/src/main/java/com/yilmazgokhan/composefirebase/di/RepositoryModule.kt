package com.yilmazgokhan.composefirebase.di

import com.yilmazgokhan.composefirebase.data.datasource.base.LoginDataSource
import com.yilmazgokhan.composefirebase.data.datasource.impl.LoginDataSourceImpl
import com.yilmazgokhan.composefirebase.data.repository.base.LoginRepository
import com.yilmazgokhan.composefirebase.data.repository.impl.LoginRepositoryImpl
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
        loginDataSource: LoginDataSource
    ): LoginRepository =
        LoginRepositoryImpl(loginDataSource)
}