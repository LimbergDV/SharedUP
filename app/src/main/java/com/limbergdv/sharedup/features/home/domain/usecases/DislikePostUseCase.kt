package com.limbergdv.sharedup.features.home.domain.usecases

import com.limbergdv.sharedup.features.addPost.domain.entities.Post
import com.limbergdv.sharedup.features.home.domain.repositories.HomeRepository
import javax.inject.Inject


class DislikePostUseCase @Inject constructor(
    private val repository: HomeRepository
){
    suspend operator fun invoke(postId: Int): Result<Post> {
    return try {
        val updatePost = repository.dislikePost(postId)
        Result.success(updatePost)
    } catch (e: Exception) {
        Result.failure(e)
        }
    }
}