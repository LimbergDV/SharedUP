package com.limbergdv.sharedup.features.addPost.domain.entities

class Post(
    val id: Int,
    val title: String,
    val text: String,
    val likeCount: Int,
    val disLikeCount: Int,
    val idUser: Int

) {

}