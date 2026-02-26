package com.limbergdv.sharedup.features.addPost.data.remote.mapper

import com.limbergdv.sharedup.features.addPost.data.datasources.remote.models.PostDto
import com.limbergdv.sharedup.features.addPost.domain.entities.Post

fun PostDto.toDomain(): Post {
    return Post(
        id = id,
        title = title,
        text = text,
        likeCount = like_count,
        disLikeCount = dislike_count,
        idUser = iduser
    )
}