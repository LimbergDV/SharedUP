package com.limbergdv.sharedup.features.authentication.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.limbergdv.sharedup.features.authentication.presentation.screens.RegisterUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun onNameChange(name: String) {
        _uiState.update { it.copy(name = name) }
    }

    fun onLastNameChange(lastName: String) {
        _uiState.update { it.copy(lastName = lastName) }
    }

    fun onEmailChange(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun onRegister() {
        val state = _uiState.value

        if (state.name.isBlank() || state.lastName.isBlank() ||
            state.email.isBlank() || state.password.isBlank()
        ) {
            _uiState.update { it.copy(error = "Todos los campos son obligatorios.") }
            return
        }

        if (!state.email.contains("@")) {
            _uiState.update { it.copy(error = "El formato del correo no es válido.") }
            return
        }

        if (state.password.length < 8) {
            _uiState.update { it.copy(error = "La contraseña debe tener al menos 8 caracteres.") }
            return
        }

        // TODO: Inyectar y llamar UseCase cuando haya API
        _uiState.update { it.copy(isSuccess = true) }
    }

    fun clearResult() {
        _uiState.update { it.copy(isSuccess = false, error = null) }
    }
}