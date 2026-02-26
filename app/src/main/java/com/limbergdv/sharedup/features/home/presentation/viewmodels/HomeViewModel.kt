package com.limbergdv.sharedup.features.home.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.limbergdv.sharedup.core.navigation.AppNavigator
import com.limbergdv.sharedup.features.addPost.navigation.AddPostRoutes
import com.limbergdv.sharedup.features.home.domain.usecases.GetPostsUseCase
import com.limbergdv.sharedup.features.home.presentation.screens.HomeUiState
import com.limbergdv.sharedup.features.myPosts.navigation.MyPostRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val navigator: AppNavigator
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getPosts()
    }

    fun getPosts() {
        _uiState.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            val result = getPostsUseCase()

            result.fold(
                onSuccess = { posts ->
                    _uiState.update {
                        it.copy(isLoading = false, posts = posts)
                    }
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(isLoading = false, error = error.message)
                    }
                }
            )
        }
    }
    fun goHome() {
        navigator.navigate("home_graph")
    }

    fun goToAddPost() {
        navigator.navigate(AddPostRoutes.ADD_POST_GRAPH)
    }

    fun goToHistory() {
        navigator.navigate(MyPostRoutes.MY_POST_GRAPH)
    }
}