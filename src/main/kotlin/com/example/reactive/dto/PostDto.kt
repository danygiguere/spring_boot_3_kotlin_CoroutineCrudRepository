package com.example.reactive.dto

import com.example.reactive.entity.Post
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class PostDto(
    @get:Size(min = 6, max = 25, message = "{title.size}")
    @get:NotNull
    val title: String,
    @get:Min(value = 6, message = "{description.min}")
    @get:Max(value = 25, message = "{description.max}")
    val description: String
)

fun PostDto.toEntity(): Post = Post(
    title = title,
    description = description
)