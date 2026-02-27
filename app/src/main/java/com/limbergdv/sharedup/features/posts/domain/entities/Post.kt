package com.limbergdv.sharedup.features.posts.domain.entities

data class Post(
    val id: Int,
    val title: String,
    val text: String,
    val likeCount: Int,
    val disLikeCount: Int,
    val idUser: Int
)