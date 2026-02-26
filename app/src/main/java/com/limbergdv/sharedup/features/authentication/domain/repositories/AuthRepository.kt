package com.limbergdv.sharedup.features.authentication.domain.repositories

import com.limbergdv.sharedup.features.authentication.domain.entities.User

interface AuthRepository {
    suspend fun login(email: String, password: String): User
    suspend fun register(name: String, email: String, password: String, career: String): User
}