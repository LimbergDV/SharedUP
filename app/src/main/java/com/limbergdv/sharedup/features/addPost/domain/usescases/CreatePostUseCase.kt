package com.limbergdv.sharedup.features.addPost.domain.usecases

import com.limbergdv.sharedup.features.addPost.domain.entities.Post
import com.limbergdv.sharedup.features.addPost.domain.repositories.PostRepository
import jakarta.inject.Inject

class CreatePostUseCase @Inject constructor(
    private val repository: PostRepository
) {
    suspend operator fun invoke(
        title: String,
        text: String
    ): Result<Post> {

        if (title.isBlank() || text.isBlank()) {
            return Result.failure(Exception("Título y contenido no pueden estar vacíos"))
        }

        return try {
            val post = repository.createPost(title, text)
            Result.success(post)
        } catch (e: Exception) {
            android.util.Log.e("NETWORK_ERROR", "Error al crear publicación", e)
            Result.failure(e)
        }
    }
}