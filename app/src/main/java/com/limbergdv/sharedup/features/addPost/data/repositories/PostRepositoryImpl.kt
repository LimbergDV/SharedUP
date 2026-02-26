package com.limbergdv.sharedup.features.addPost.data.repositories

import com.limbergdv.sharedup.features.addPost.data.datasources.remote.api.SharedUpApi
import com.limbergdv.sharedup.features.addPost.data.datasources.remote.models.CreatePostRequest

import com.limbergdv.sharedup.features.addPost.data.remote.mapper.toDomain

import com.limbergdv.sharedup.features.addPost.domain.entities.Post
import com.limbergdv.sharedup.features.addPost.domain.repositories.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val api: SharedUpApi
) : PostRepository {

    override suspend fun createPost(
        title: String,
        text: String
    ): Post {

        val response = api.createPost(
            CreatePostRequest(
                title = title,
                text = text
            )
        )

        return response.toDomain()
    }
}