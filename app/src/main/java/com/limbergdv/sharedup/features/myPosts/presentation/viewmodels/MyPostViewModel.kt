package com.limbergdv.sharedup.features.myPosts.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.limbergdv.sharedup.core.navigation.AppNavigator
import com.limbergdv.sharedup.features.addPost.navigation.AddPostRoutes
import com.limbergdv.sharedup.features.home.navigation.HomeRoutes
import com.limbergdv.sharedup.features.myPosts.domain.entities.Post
import com.limbergdv.sharedup.features.myPosts.domain.usecases.GetPostByUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPostViewModel @Inject constructor(
    private val navigator: AppNavigator,
    private val getPostByUser: GetPostByUser
): ViewModel() {

    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun loadMyPosts() {
        viewModelScope.launch {
            _isLoading.value = true

            val result = getPostByUser()

            result.onSuccess {
                _posts.value = it
            }

            _isLoading.value = false
        }
    }
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