package com.limbergdv.sharedup.features.myPosts.data.repositories

import com.limbergdv.sharedup.features.myPosts.data.datasources.remote.api.SharedUpApi
import com.limbergdv.sharedup.features.myPosts.data.mappers.toDomain
import com.limbergdv.sharedup.features.myPosts.domain.entities.Post
import com.limbergdv.sharedup.features.myPosts.domain.repositories.MyPostRepository
import javax.inject.Inject

class MyPostRepositoryImpl @Inject constructor(
    private val api: SharedUpApi
) : MyPostRepository {

    override suspend fun getMyPosts(): List<Post> {
        val response = api.getMyPosts()
        return response.map { it.toDomain() }
    }
}