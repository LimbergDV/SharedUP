package com.limbergdv.sharedup.features.addPost.data.datasources.remote.api

import com.limbergdv.sharedup.features.addPost.data.datasources.remote.models.CreatePostRequest
import com.limbergdv.sharedup.features.addPost.data.datasources.remote.models.PostDto
import retrofit2.http.Body
import retrofit2.http.POST

interface SharedUpApi {
    @POST("api/v1/post")
    suspend fun createPost(
        @Body request: CreatePostRequest
    ): PostDto
}