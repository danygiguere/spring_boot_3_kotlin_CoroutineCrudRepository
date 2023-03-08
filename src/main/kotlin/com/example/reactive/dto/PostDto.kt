package com.example.reactive.dto

import com.example.reactive.entity.Post
import jakarta.validation.constraints.Size

data class PostDto(
    @get:Size(min = 6, max = 25, message = "message")
    val title: String,
    val description: String
)

fun PostDto.toEntity(): Post = Post(
    title = title,
    description = description
)