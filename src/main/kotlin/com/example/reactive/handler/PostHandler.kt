package com.example.reactive.handler

import com.example.reactive.dto.PostDto
import com.example.reactive.dto.toEntity
import com.example.reactive.entity.toDto
import com.example.reactive.repository.PostRepository
import jakarta.validation.ConstraintViolation
import jakarta.validation.Valid
import jakarta.validation.Validator
import kotlinx.coroutines.flow.map
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.server.*

@RestController
class PostHandler(
    private val postRepository: PostRepository
) {

    companion object: KLogging()

    @Autowired
    private val validator: Validator? = null

    suspend fun getAll(req: ServerRequest): ServerResponse {
        return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyAndAwait(
                postRepository.findAll().map { it.toDto() }
            )
    }

    suspend fun getById(req: ServerRequest): ServerResponse {
        val id = Integer.parseInt(req.pathVariable("id"))
        val post = postRepository.findById(id.toLong())

        return post?.let {
            ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValueAndAwait(it)
        } ?: ServerResponse.notFound().buildAndAwait()
    }

    suspend fun add(@RequestBody req : ServerRequest): ServerResponse {
        val post = req.awaitBodyOrNull(PostDto::class)
        val validatedPost: MutableSet<ConstraintViolation<PostDto?>>? = validator?.validate(post)
        if (validatedPost != null) {
            if (validatedPost.isNotEmpty()) {
                logger.info("validation failed: $validatedPost")
                for (error in validatedPost) {
                    logger.info("validation error: ${error.message}")
                }
            } else {
                logger.info("validation passed: $validatedPost")
            }
        }
        return post?.let {
            ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValueAndAwait(
                    postRepository
                        .save(it.toEntity())
                        .toDto()
                )
        } ?: ServerResponse.badRequest().buildAndAwait()
    }

    suspend fun update(@Valid @RequestBody req: ServerRequest): ServerResponse {
        val id = req.pathVariable("id")

        val receivedPost = req.awaitBodyOrNull(PostDto::class)
            ?: return ServerResponse.badRequest().buildAndAwait()

        val post = postRepository.findById(id.toLong())
            ?: return ServerResponse.notFound().buildAndAwait()

        return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(
                postRepository.save(
                    receivedPost.toEntity().copy(id = post.id)
                ).toDto()
            )
    }

    suspend fun delete(req: ServerRequest): ServerResponse {
        val id = req.pathVariable("id")

        return if (postRepository.existsById(id.toLong())) {
            postRepository.deleteById(id.toLong())
            ServerResponse.noContent().buildAndAwait()
        } else {
            ServerResponse.notFound().buildAndAwait()
        }
    }
}