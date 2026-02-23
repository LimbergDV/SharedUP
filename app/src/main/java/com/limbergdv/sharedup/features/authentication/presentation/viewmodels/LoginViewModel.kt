package com.limbergdv.sharedup.features.authentication.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.limbergdv.sharedup.features.authentication.presentation.screens.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

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

        // TODO: Inyectar y llamar UseCase cuando haya API
        _uiState.update { it.copy(isLoggedIn = true) }
    }

    fun clearResult() {
        _uiState.update { it.copy(error = null, isLoggedIn = false) }
    }
}