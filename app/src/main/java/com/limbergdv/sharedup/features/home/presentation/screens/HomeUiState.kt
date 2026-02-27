package com.limbergdv.sharedup.features.home.presentation.screens

import com.limbergdv.sharedup.features.home.domain.entities.Post

data class HomeUiState(
    val isLoading: Boolean = false,
    val posts: List<Post> = emptyList(),
    val error: String? = null
)