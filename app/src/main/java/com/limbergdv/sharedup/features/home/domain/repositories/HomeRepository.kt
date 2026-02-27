package com.limbergdv.sharedup.features.home.domain.repositories


import com.limbergdv.sharedup.features.home.domain.entities.Post

interface HomeRepository {
    suspend fun getPosts(): List<Post>
    suspend fun likePost(postId: Int): Post
    suspend fun dislikePost(postId: Int): Post
}