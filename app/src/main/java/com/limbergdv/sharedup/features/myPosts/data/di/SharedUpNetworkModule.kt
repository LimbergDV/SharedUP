package com.limbergdv.sharedup.features.myPosts.data.di

import com.limbergdv.sharedup.features.myPosts.data.datasources.remote.api.SharedUpApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object SharedUpNetworkModule {

    @Provides
    @Singleton
    fun provideMyPostApi(retrofit: Retrofit): SharedUpApi {
        return retrofit.create(SharedUpApi::class.java)
    }
}