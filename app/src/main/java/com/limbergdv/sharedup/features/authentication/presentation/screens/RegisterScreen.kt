package com.limbergdv.sharedup.features.authentication.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.limbergdv.sharedup.core.ui.theme.primaryLight
import com.limbergdv.sharedup.features.authentication.presentation.components.DialogError
import com.limbergdv.sharedup.features.authentication.presentation.components.DialogSuccess
import com.limbergdv.sharedup.features.authentication.presentation.viewmodels.RegisterViewModel

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),

) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(primaryLight)
    ) {
        Spacer(modifier = Modifier.height(54.dp))

        Text(
            text = "Crear cuenta",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Comienza a usar la app",
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(18.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White, RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .padding(24.dp)
        ) {
            Text(
                text = "← Inicio de sesión",
                fontSize = 14.sp,
                color = primaryLight,
                modifier = Modifier.clickable { viewModel.goToLogin() }
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Registro",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = primaryLight
            )

            Spacer(modifier = Modifier.height(20.dp))

            AuthTextField(
                value = uiState.name,
                onValueChange = viewModel::onNameChange,
                placeholder = "Nombre"
            )

            Spacer(modifier = Modifier.height(16.dp))

            AuthTextField(
                value = uiState.career,
                onValueChange = viewModel::onCareerChange,
                placeholder = "Carrera"
            )

            Spacer(modifier = Modifier.height(16.dp))

            AuthTextField(
                value = uiState.email,
                onValueChange = viewModel::onEmailChange,
                placeholder = "Correo electrónico"
            )

            Spacer(modifier = Modifier.height(16.dp))

            AuthTextField(
                value = uiState.password,
                onValueChange = viewModel::onPasswordChange,
                placeholder = "Contraseña",
                isPassword = true
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = viewModel::onRegister,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                enabled = !uiState.isLoading,
                colors = ButtonDefaults.buttonColors(containerColor = primaryLight),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = if (uiState.isLoading) "Procesando..." else "Registrar",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

    // Dialog de éxito — al aceptar navega al login
    if (uiState.isSuccess) {
        DialogSuccess(
            title = "¡Registro exitoso!",
            message = "Tu cuenta fue creada correctamente. Inicia sesión para continuar.",
            buttonText = "Ir a iniciar sesión",
            onDismiss = {
                viewModel.onRegisterSuccessConfirmed()
            }
        )
    }

    // Dialog de error con título dinámico según el tipo de error
    uiState.error?.let { errorMsg ->
        val title = when {
            errorMsg.contains("obligatorio", ignoreCase = true) -> "Campos vacíos"
            errorMsg.contains("correo", ignoreCase = true) -> "Correo inválido"
            errorMsg.contains("contraseña", ignoreCase = true) -> "Contraseña muy corta"
            errorMsg.contains("registrado", ignoreCase = true) -> "Correo ya registrado"
            else -> "Error en el registro"
        }
        DialogError(
            title = title,
            message = errorMsg,
            buttonText = "Intentar de nuevo",
            onDismiss = { viewModel.clearResult() }
        )
    }
}

@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isPassword: Boolean = false
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(placeholder) },
        shape = RoundedCornerShape(12.dp),
        singleLine = true,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color.LightGray,
            focusedBorderColor = primaryLight
        )
    )
}