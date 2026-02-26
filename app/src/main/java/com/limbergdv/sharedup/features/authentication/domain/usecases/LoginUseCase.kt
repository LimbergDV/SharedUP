package com.limbergdv.sharedup.features.authentication.domain.usecases

import com.limbergdv.sharedup.features.authentication.domain.entities.User
import com.limbergdv.sharedup.features.authentication.domain.repositories.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): Result<User> {
        return try {
            val user = repository.login(email, password)
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}