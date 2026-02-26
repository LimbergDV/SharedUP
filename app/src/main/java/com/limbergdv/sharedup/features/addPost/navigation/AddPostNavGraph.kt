package com.limbergdv.sharedup.features.addPost.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.limbergdv.sharedup.core.navigation.FeatureNavGraph
import com.limbergdv.sharedup.features.addPost.presentation.screens.AddPostScreen
import javax.inject.Inject

class AddPostNavGraph @Inject constructor() : FeatureNavGraph {

    override fun register(builder: NavGraphBuilder) {
        builder.navigation(
            route = AddPostRoutes.ADD_POST_GRAPH,
            startDestination = AddPostRoutes.ADD_POST
        ) {
            composable(AddPostRoutes.ADD_POST) {
                AddPostScreen()
            }
        }
    }
}