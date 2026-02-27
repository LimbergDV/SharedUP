package com.limbergdv.sharedup.features.home.data.datasources.remote.api


import com.limbergdv.sharedup.features.home.data.datasources.remote.models.PostDto
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface HomeApi {
    @GET("api/v1/post/")
    suspend fun getPosts(): List<PostDto>

    @PUT("api/v1/post/{id}/like")
    suspend fun likePost(@Path("id") postId: Int): PostDto

    @PUT("api/v1/post/{id}/dislike")
    suspend fun dislikePost(@Path("id") postId: Int): PostDto

}