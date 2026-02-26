package com.limbergdv.sharedup.features.addPost.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.limbergdv.sharedup.features.addPost.domain.usecases.CreatePostUseCase
import com.limbergdv.sharedup.features.addPost.presentation.screens.AddPostUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class AddPostViewModel @Inject constructor(
    private val createPostUseCase: CreatePostUseCase
) : ViewModel() {


    private val _uiState = MutableStateFlow(AddPostUiState())
    val uiState = _uiState.asStateFlow()

    private val _title = MutableStateFlow("")
    val title = _title.asStateFlow()

    private val _text = MutableStateFlow("")
    val text = _text.asStateFlow()

    fun onTitleChange(value: String) {
        _title.value = value
    }

    fun onTextChange(value: String) {
        _text.value = value
    }


    fun createPost() {

        _uiState.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            val result = createPostUseCase(title.value, text.value)

            _uiState.update { state ->
                result.fold(
                    onSuccess = {
                        state.copy(
                            isLoading = false,
                            success = true
                        )
                    },
                    onFailure = {
                        state.copy(
                            isLoading = false,
                            error = it.message
                        )
                    }
                )
            }
        }
    }
    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }

    fun clearSuccess() {
        _uiState.update { it.copy(success = false) }
    }
}