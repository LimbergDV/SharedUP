package com.limbergdv.sharedup.features.home.data.datasources.remote.models

data class PostDto(
    val id: Int?,
    val title: String?,
    val text: String?,
    val like_count: Int?,
    val dislike_count: Int?,
    val iduser: Int,
    val user_name: String,
    val user_career: String,
    val created_at: String,

)

data class CreatePostRequest(
    val title: String,
    val text: String,
    val like_count: Int = 0,
    val dislike_count: Int = 0
)