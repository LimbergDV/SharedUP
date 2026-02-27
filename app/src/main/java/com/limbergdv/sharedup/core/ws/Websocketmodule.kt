package com.limbergdv.sharedup.core.ws

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WebSocketModule {

    @Provides
    @Singleton
    fun providePostWebSocketManager(
        okHttpClient: OkHttpClient // Se reutiliza el mismo OkHttpClient
    ): PostWebSocketManager {
        return PostWebSocketManager(okHttpClient)
    }
}