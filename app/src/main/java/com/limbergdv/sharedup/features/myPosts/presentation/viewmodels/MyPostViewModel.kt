package com.limbergdv.sharedup.features.myPosts.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.limbergdv.sharedup.core.navigation.AppNavigator
import com.limbergdv.sharedup.features.addPost.navigation.AddPostRoutes
import com.limbergdv.sharedup.features.home.navigation.HomeRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyPostViewModel @Inject constructor(
    private val navigator: AppNavigator
): ViewModel() {
    fun goHome() {
        navigator.navigate(HomeRoutes.HOME_GRAPH)
    }

    fun goToAddPost() {
        navigator.navigate(AddPostRoutes.ADD_POST_GRAPH)
    }

    fun goToHistory() {
        navigator.navigate("my_post")
    }
}