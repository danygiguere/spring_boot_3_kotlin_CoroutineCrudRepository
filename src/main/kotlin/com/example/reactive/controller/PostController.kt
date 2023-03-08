package com.example.reactive.controller

import com.example.reactive.dto.PostDto
import com.example.reactive.entity.Post
import com.example.reactive.repository.PostRepository
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@Validated
class PostController(private val postRepository: PostRepository) {

    @GetMapping("/posts/{id}")
    suspend fun getPostById(@PathVariable id: Long): ResponseEntity<Post>? {
        return postRepository.findById(id)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    @PostMapping("/posts")
    suspend fun create(@Valid @RequestBody postDto: PostDto): ResponseEntity<Post>? {
        val post = postRepository.save(Post(null, postDto.title, postDto.description))
        return ResponseEntity.ok(post)
    }
}