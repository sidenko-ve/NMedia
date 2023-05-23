package ru.netology.nmedia.dto

data class Post(
    var id: Long,
    val author: String,
    val content: String,
    val published: String,
    val likedByMe: Boolean = false,
    val likes: Long,
    val shares: Long,
    val views: Long
)
