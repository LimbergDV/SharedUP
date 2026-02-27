package com.limbergdv.sharedup.features.authentication.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.limbergdv.sharedup.core.navigation.AppNavigator
import com.limbergdv.sharedup.features.authentication.domain.usecases.RegisterUseCase
import com.limbergdv.sharedup.features.authentication.navigation.AuthRoutes
import com.limbergdv.sharedup.features.authentication.presentation.screens.RegisterUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val navigator: AppNavigator
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun onNameChange(name: String) {
        _uiState.update { it.copy(name = name) }
    }

    fun onCareerChange(career: String) {
        _uiState.update { it.copy(career = career) }
    }

    fun onEmailChange(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun onRegister() {
        val state = _uiState.value

        // Validaciones locales
        if (state.name.isBlank() || state.career.isBlank() ||
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

        _uiState.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            val result = registerUseCase(
                name = state.name,
                email = state.email,
                password = state.password,
                career = state.career
            )
            _uiState.update { current ->
                result.fold(
                    onSuccess = {
                        navigator.navigate(AuthRoutes.LOGIN) {
                            popUpTo(AuthRoutes.REGISTER) { inclusive = true }
                        }
                        current.copy(isLoading = false)
                    },
                    onFailure = { e -> current.copy(isLoading = false, error = e.message ?: "Error en el registro") }
                )
            }
        }
    }

    fun clearResult() {
        _uiState.update { it.copy(isSuccess = false, error = null) }
    }
    fun goToLogin() {
        navigator.navigate(AuthRoutes.LOGIN) {
            popUpTo(AuthRoutes.REGISTER) { inclusive = true }
        }
    }
    fun onRegisterSuccessConfirmed() {

        goToLogin()

    }
}