package com.example.reactive.entity
import com.example.reactive.dto.PostDto
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
data class Post(
    @Id var id: Long? = null,
    val title: String,
    val description: String,
)

fun Post.toDto(): PostDto = PostDto(
    title = title,
    description = description,
)