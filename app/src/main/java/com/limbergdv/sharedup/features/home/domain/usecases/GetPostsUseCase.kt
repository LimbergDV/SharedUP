package com.limbergdv.sharedup.features.home.domain.usecases

import com.limbergdv.sharedup.features.addPost.domain.entities.Post
import com.limbergdv.sharedup.features.home.domain.repositories.HomeRepository
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(): Result<List<Post>> {
        return try {
            val posts = repository.getPosts()
            Result.success(posts)
        } catch (e: Exception) {
            android.util.Log.e("NETWORK_ERROR", "Error al obtener publicaciones", e)
            Result.failure(e)
        }
    }
}