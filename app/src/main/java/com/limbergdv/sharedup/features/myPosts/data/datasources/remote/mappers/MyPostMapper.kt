package com.limbergdv.sharedup.features.myPosts.data.mappers

import com.limbergdv.sharedup.features.myPosts.data.datasources.remote.models.MyPostDto
import com.limbergdv.sharedup.features.myPosts.domain.entities.Post

fun MyPostDto.toDomain(): Post {
    return Post(
        id = id ?: 0,
        title = title ?: "Sin título",
        text = text ?: "",
        likeCount = like_count ?: 0,
        disLikeCount = dislike_count ?: 0,
        idUser = iduser ?: 0
    )
}