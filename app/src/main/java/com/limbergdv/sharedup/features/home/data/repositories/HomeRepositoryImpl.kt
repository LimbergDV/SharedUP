package com.limbergdv.sharedup.features.home.data.repositories


import com.limbergdv.sharedup.features.home.domain.entities.Post
import com.limbergdv.sharedup.features.home.data.datasources.remote.api.HomeApi
import com.limbergdv.sharedup.features.home.data.datasources.remote.mappers.toDomain
import com.limbergdv.sharedup.features.home.domain.repositories.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val api: HomeApi
) : HomeRepository {

    override suspend fun getPosts(): List<Post> {
        return api.getPosts().map { it.toDomain() }
    }

    override suspend fun likePost(postId: Int): Post {
        return api.likePost(postId).toDomain()
    }

    override suspend fun dislikePost(postId: Int): Post {
        return api.dislikePost(postId).toDomain()
    }
}