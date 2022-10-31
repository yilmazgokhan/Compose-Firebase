package com.yilmazgokhan.composefirebase.di

import com.yilmazgokhan.composefirebase.data.repository.base.LoginRepository
import com.yilmazgokhan.composefirebase.data.repository.base.RegisterRepository
import com.yilmazgokhan.composefirebase.domain.sdk.AuthService
import com.yilmazgokhan.composefirebase.domain.usecase.LoginUseCase
import com.yilmazgokhan.composefirebase.domain.usecase.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @ViewModelScoped
    @Provides
    fun provideLoginUseCase(
        loginRepository: LoginRepository,
    ) = LoginUseCase(loginRepository)

    @ViewModelScoped
    @Provides
    fun provideRegisterUseCase(
        authService: AuthService,
        registerRepository: RegisterRepository,
    ) = RegisterUseCase(authService, registerRepository)
}