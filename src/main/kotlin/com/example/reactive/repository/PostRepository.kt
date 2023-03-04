package com.example.reactive.repository

import com.example.reactive.entity.Post
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository


interface PostRepository : CoroutineCrudRepository<Post, Long> {
    override fun findAll(): Flow<Post>
    override suspend fun findById(id: Long): Post?
    override suspend fun existsById(id: Long): Boolean
    override suspend fun <S : Post> save(entity: S): Post
    override suspend fun deleteById(id: Long)
}