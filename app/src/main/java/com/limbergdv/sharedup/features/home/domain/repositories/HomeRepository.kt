package com.limbergdv.sharedup.features.home.domain.repositories


import com.limbergdv.sharedup.features.addPost.domain.entities.Post

interface HomeRepository {
    suspend fun getPosts(): List<Post>
}