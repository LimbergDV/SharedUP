package com.limbergdv.sharedup.features.addPost.data.datasources.remote.models

data class PostDto(
    val id: Int?,
    val title: String?,
    val text: String?,
    val like_count: Int?,
    val dislike_count: Int?,
    val iduser: Int?
)

data class CreatePostRequest(
    val title: String,
    val text: String,
    val like_count: Int = 0,
    val dislike_count: Int = 0
)