package com.limbergdv.sharedup.features.addPost.data.di

import com.limbergdv.sharedup.features.addPost.data.repositories.PostRepositoryImpl
import com.limbergdv.sharedup.features.addPost.domain.repositories.PostRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindPostRepository(
        impl: PostRepositoryImpl
    ): PostRepository
}