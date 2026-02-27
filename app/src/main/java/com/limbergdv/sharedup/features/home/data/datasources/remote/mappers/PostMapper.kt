package com.limbergdv.sharedup.features.home.data.datasources.remote.mappers


import com.limbergdv.sharedup.features.home.data.datasources.remote.models.PostDto
import com.limbergdv.sharedup.features.home.domain.entities.Post

fun PostDto.toDomain(): Post {
    return Post(
        id = id ?: 0, // Si id es null, pon 0
        title = title ?: "Sin título", // Si title es null, pon un texto por defecto
        text = text ?: "",
        likeCount = like_count ?: 0,
        disLikeCount = dislike_count ?: 0,
        idUser = iduser,
        userName = user_name,
        userCareer = user_career,
        createdAt = created_at
    )
}