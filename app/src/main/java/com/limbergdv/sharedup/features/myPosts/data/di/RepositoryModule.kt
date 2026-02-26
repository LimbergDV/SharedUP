package com.limbergdv.sharedup.features.myPosts.data.di

import com.limbergdv.sharedup.features.myPosts.data.repositories.MyPostRepositoryImpl
import com.limbergdv.sharedup.features.myPosts.domain.repositories.MyPostRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMyPostRepository(
        impl: MyPostRepositoryImpl
    ): MyPostRepository
}