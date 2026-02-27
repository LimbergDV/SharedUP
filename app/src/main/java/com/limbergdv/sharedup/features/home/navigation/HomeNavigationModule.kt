package com.limbergdv.sharedup.features.home.navigation

import com.limbergdv.sharedup.core.navigation.FeatureNavGraph
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeNavigationModule {

    @Binds
    @IntoSet
    abstract fun bindHomeNavGraph(graph: HomeNavGraph): FeatureNavGraph
}