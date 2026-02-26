package com.limbergdv.sharedup.features.authentication.presentation.screens

data class RegisterUiState(
    val name: String = "",
    val career: String = "",
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null
)