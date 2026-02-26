package com.limbergdv.sharedup.features.authentication.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.limbergdv.sharedup.features.authentication.domain.usecases.LoginUseCase
import com.limbergdv.sharedup.features.authentication.presentation.screens.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChange(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun login() {
        val state = _uiState.value

        if (state.email.isBlank() || state.password.isBlank()) {
            _uiState.update { it.copy(error = "El correo y la contraseña son obligatorios.") }
            return
        }

        _uiState.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            val result = loginUseCase(state.email, state.password)
            _uiState.update { current ->
                result.fold(
                    onSuccess = { current.copy(isLoading = false, isLoggedIn = true) },
                    onFailure = { e -> current.copy(isLoading = false, error = e.message ?: "Error al iniciar sesión") }
                )
            }
        }
    }

    fun clearResult() {
        _uiState.update { it.copy(error = null, isLoggedIn = false) }
    }
}