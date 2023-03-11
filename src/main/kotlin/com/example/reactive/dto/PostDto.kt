package com.example.reactive.dto

import com.example.reactive.entity.Post
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class PostDto(
    @get:NotEmpty()
    @get:Size(min = 6, max = 25, message = "{title.size}")
    val title: String,

    @get:NotEmpty()
    @get:Size(min = 6, max = 25, message = "{description.size}")
    val description: String
)

fun PostDto.toEntity(): Post = Post(
    title = title,
    description = description
)