package com.limbergdv.sharedup.features.myPosts.domain.usecases

import android.util.Log
import com.limbergdv.sharedup.features.myPosts.domain.entities.Post
import com.limbergdv.sharedup.features.myPosts.domain.repositories.MyPostRepository
import jakarta.inject.Inject

class GetPostByUser @Inject constructor(
    private val repository: MyPostRepository
) {

    suspend operator fun invoke(): Result<List<Post>> {

        return try {
            val posts = repository.getMyPosts()
            Result.success(posts)
        } catch (e: Exception) {
            Log.e("NETWORK_ERROR", "Error al obtener publicaciones", e)
            Result.failure(e)
        }
    }
}