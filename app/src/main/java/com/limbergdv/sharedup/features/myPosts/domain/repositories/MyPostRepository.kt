package com.limbergdv.sharedup.features.myPosts.domain.repositories

import com.limbergdv.sharedup.features.myPosts.domain.entities.Post

interface MyPostRepository {
    suspend fun getMyPosts(): List<Post>
}