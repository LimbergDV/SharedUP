package com.limbergdv.sharedup.features.home.domain.entities

data class Post(
    val id: Int,
    val title: String,
    val text: String,
    val likeCount: Int,
    val disLikeCount: Int,
    val idUser: Int,
    val isLiked: Boolean = false,
    val isDisliked: Boolean = false
)