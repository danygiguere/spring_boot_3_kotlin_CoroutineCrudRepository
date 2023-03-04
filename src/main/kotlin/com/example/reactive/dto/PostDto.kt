package com.example.reactive.dto

import com.example.reactive.entity.Post

data class PostDto(
    val title: String,
    val description: String
)

fun PostDto.toEntity(): Post = Post(
    title = title,
    description = description
)