package com.limbergdv.sharedup.features.myPosts.domain.entities

class Post(
    val id: Int,
    val title: String,
    val text: String,
    val likeCount: Int,
    val disLikeCount: Int,
    val idUser: Int

) {

}