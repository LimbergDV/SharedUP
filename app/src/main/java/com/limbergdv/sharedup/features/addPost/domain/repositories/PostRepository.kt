package com.limbergdv.sharedup.features.addPost.domain.repositories

import com.limbergdv.sharedup.features.addPost.domain.entities.Post

interface PostRepository {
    suspend fun createPost(
        title: String,
        text: String
    ): Post
}