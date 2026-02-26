package com.limbergdv.sharedup.features.authentication.data.datasources.remote.mappers

import com.limbergdv.sharedup.features.authentication.data.datasources.remote.models.UserRegisterDto
import com.limbergdv.sharedup.features.authentication.data.datasources.remote.models.UserDto
import com.limbergdv.sharedup.features.authentication.domain.entities.User



// Mapper de Dominio a DTO (Para enviar datos al server)
fun User.toRegisterDto(): UserRegisterDto {
    return UserRegisterDto(
        name = this.name,
        career = this.career,
        email = this.email,
        password = this.password
    )
}

/**
 * Mapper de DTO a Dominio (Para recibir datos del server)
 * Se agrega el "?" en UserDto para que acepte nulos y no truene la app.
 */
fun UserDto?.toDomain(): User? {
    // Si el objeto es nulo, regresamos nulo de forma segura
    if (this == null) return null

    return User(
        name = this.name ?: "",      // Si el campo individual llega nulo, ponemos texto vacío
        career = this.career ?: "",
        email = this.email ?: "",
        password = "" // El backend no devuelve la contraseña por seguridad
    )
}