package com.limbergdv.sharedup.features.authentication.domain.usecases

import com.limbergdv.sharedup.features.authentication.domain.entities.User
import com.limbergdv.sharedup.features.authentication.domain.repositories.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(name: String, email: String, password: String, career: String): Result<User> {
        return try {
            val user = repository.register(name, email, password, career)
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}