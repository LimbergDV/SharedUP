package com.limbergdv.sharedup.features.addPost.domain.entities

// Cambiamos a "data class" y agregamos isLiked e isDisliked
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