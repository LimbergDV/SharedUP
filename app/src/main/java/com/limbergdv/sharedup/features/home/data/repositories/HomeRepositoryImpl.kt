package com.limbergdv.sharedup.features.home.data.repositories

import com.limbergdv.sharedup.features.addPost.data.remote.mapper.toDomain
import com.limbergdv.sharedup.features.addPost.domain.entities.Post
import com.limbergdv.sharedup.features.home.data.datasources.remote.api.HomeApi
import com.limbergdv.sharedup.features.home.domain.repositories.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val api: HomeApi
) : HomeRepository {

    override suspend fun getPosts(): List<Post> {
        val response = api.getPosts()
        // Reutilizamos tu mapper existente para convertir los DTOs a la entidad de dominio
        return response.map { it.toDomain() }
    }
}