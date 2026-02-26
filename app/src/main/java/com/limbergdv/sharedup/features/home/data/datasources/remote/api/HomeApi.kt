package com.limbergdv.sharedup.features.home.data.datasources.remote.api

import com.limbergdv.sharedup.features.addPost.data.datasources.remote.models.PostDto
import retrofit2.http.GET

interface HomeApi {
    @GET("api/v1/post/")
    suspend fun getPosts(): List<PostDto>
}