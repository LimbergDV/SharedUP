package com.limbergdv.sharedup.features.authentication.di

import com.limbergdv.sharedup.features.authentication.data.repositories.AuthRepositoryImpl
import com.limbergdv.sharedup.features.authentication.domain.repositories.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository
}