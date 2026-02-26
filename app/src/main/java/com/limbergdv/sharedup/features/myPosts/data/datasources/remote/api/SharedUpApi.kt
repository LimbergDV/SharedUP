package com.limbergdv.sharedup.features.myPosts.data.datasources.remote.api

import com.limbergdv.sharedup.features.myPosts.data.datasources.remote.models.MyPostDto
import retrofit2.http.GET

interface SharedUpApi {

    @GET("api/v1/post/me")
    suspend fun getMyPosts(): List<MyPostDto>
}