package com.limbergdv.sharedup.features.authentication.data.datasources.remote.models

data class UserRegisterDto(
    val name: String,
    val career: String,
    val email: String,
    val password: String
)

data class LoginRequestDto(
    val email: String,
    val password: String
)

// Lo que recibes del servidor (Login y Registro suelen devolver lo mismo)
data class AuthResponse(
    val data: UserDto?,
    val token: String?   // Nullable por si el backend omite el token en algún caso
)

// El usuario que viene DENTRO de la respuesta
data class UserDto(
    val name: String?,
    val career: String?,
    val email: String?
)