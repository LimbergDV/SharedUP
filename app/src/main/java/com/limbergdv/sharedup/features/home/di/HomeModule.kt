package com.limbergdv.sharedup.features.home.di

import com.limbergdv.sharedup.features.home.data.datasources.remote.api.HomeApi
import com.limbergdv.sharedup.features.home.data.repositories.HomeRepositoryImpl
import com.limbergdv.sharedup.features.home.domain.repositories.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun provideHomeApi(retrofit: Retrofit): HomeApi {
        return retrofit.create(HomeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHomeRepository(api: HomeApi): HomeRepository {
        return HomeRepositoryImpl(api)
    }
}