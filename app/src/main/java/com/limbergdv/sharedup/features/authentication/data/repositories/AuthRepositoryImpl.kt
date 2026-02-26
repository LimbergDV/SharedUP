package com.limbergdv.sharedup.features.authentication.data.repositories

import android.util.Log
import com.limbergdv.sharedup.core.network.SharedUpApi
import com.limbergdv.sharedup.core.session.TokenDataStore
import com.limbergdv.sharedup.features.authentication.data.datasources.remote.mappers.toDomain
import com.limbergdv.sharedup.features.authentication.data.datasources.remote.mappers.toRegisterDto
import com.limbergdv.sharedup.features.authentication.data.datasources.remote.models.LoginRequestDto
import com.limbergdv.sharedup.features.authentication.domain.entities.User
import com.limbergdv.sharedup.features.authentication.domain.repositories.AuthRepository
import org.json.JSONObject
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: SharedUpApi,
    private val tokenDataStore: TokenDataStore
) : AuthRepository {

    override suspend fun login(email: String, password: String): User {
        val response = api.login(LoginRequestDto(email, password))

        // Guardar token
        response.token?.let { tokenDataStore.saveToken(it) }
        Log.d("AUTH", "Token guardado tras login")

        return response.data?.toDomain()
            ?: throw Exception("Credenciales inválidas")
    }

    override suspend fun register(
        name: String,
        email: String,
        password: String,
        career: String
    ): User {
        val user = User(name = name, email = email, password = password, career = career)
        val response = api.register(user.toRegisterDto())

        if (response.isSuccessful) {
            val body = response.body()
                ?: throw Exception("Respuesta del servidor vacía")

            // Guardar token si viene
            body.token?.let { tokenDataStore.saveToken(it) }
            Log.d("AUTH", "Token guardado tras registro")

            return body.data?.toDomain() ?: user

        } else {
            val errorBodyString = response.errorBody()?.string()
            val errorMessage = try {
                val json = JSONObject(errorBodyString ?: "")
                json.optString("message", "Error desconocido en el servidor")
            } catch (e: Exception) {
                when (response.code()) {
                    400 -> "Datos inválidos. Revisa los campos ingresados."
                    409 -> "El correo ya está registrado."
                    500 -> "Error interno del servidor. Inténtalo más tarde."
                    else -> "Error inesperado: ${response.code()}"
                }
            }
            throw Exception(errorMessage)
        }
    }
}