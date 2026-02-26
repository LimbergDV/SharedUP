package com.limbergdv.sharedup.features.myPosts.data.datasources.remote.models

data class MyPostDto(
    val id: Int?,
    val title: String?,
    val text: String?,
    val like_count: Int?,
    val dislike_count: Int?,
    val iduser: Int?
)