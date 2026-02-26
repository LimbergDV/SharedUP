package com.limbergdv.sharedup.features.myPosts.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.limbergdv.sharedup.core.navigation.FeatureNavGraph
import com.limbergdv.sharedup.features.myPosts.presentation.screens.MyPostScreen
import javax.inject.Inject

class MyPostNavGraph @Inject constructor() : FeatureNavGraph {

    override fun register(builder: NavGraphBuilder) {
        builder.navigation(
            route = MyPostRoutes.MY_POST_GRAPH,
            startDestination = MyPostRoutes.MY_POST
        ) {
            composable(MyPostRoutes.MY_POST) {
                MyPostScreen()
            }
        }
    }
}