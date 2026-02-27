package com.limbergdv.sharedup.features.addPost.presentation.screens

data class AddPostUiState(
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val error: String? = null
)