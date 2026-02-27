package com.limbergdv.sharedup.features.home.domain.usecases

import com.limbergdv.sharedup.features.home.domain.entities.Post
import com.limbergdv.sharedup.features.home.domain.repositories.HomeRepository
import javax.inject.Inject

class LikePostUseCase @Inject constructor(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(postId: Int): Result<Post> {
        return try {
            val updatedPost = repository.likePost(postId)
            Result.success(updatedPost)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}