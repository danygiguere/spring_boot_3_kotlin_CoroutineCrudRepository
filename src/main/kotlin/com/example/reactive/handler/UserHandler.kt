package com.example.reactive.handler

import com.example.reactive.dto.UserDto
import com.example.reactive.dto.toEntity
import com.example.reactive.entity.toDto
import com.example.reactive.repository.UserRepository
import kotlinx.coroutines.flow.map
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBodyOrNull
import org.springframework.web.reactive.function.server.bodyAndAwait
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.buildAndAwait

@Component
class UserHandler(
    private val userRepository: UserRepository
) {
    suspend fun getAll(req: ServerRequest): ServerResponse {
        return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyAndAwait(
                userRepository.findAll().map { it.toDto() }
            )
    }

    suspend fun getById(req: ServerRequest): ServerResponse {
        val id = Integer.parseInt(req.pathVariable("id"))
        val user = userRepository.findById(id.toLong())

        return user?.let {
            ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValueAndAwait(it)
        } ?: ServerResponse.notFound().buildAndAwait()
    }

    suspend fun add(req: ServerRequest): ServerResponse {
        val user = req.awaitBodyOrNull(UserDto::class)

        return user?.let {
            ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValueAndAwait(
                    userRepository
                        .save(it.toEntity())
                        .toDto()
                )
        } ?: ServerResponse.badRequest().buildAndAwait()
    }

    suspend fun update(req: ServerRequest): ServerResponse {
        val id = req.pathVariable("id")

        val receivedUser = req.awaitBodyOrNull(UserDto::class)
            ?: return ServerResponse.badRequest().buildAndAwait()

        val user = userRepository.findById(id.toLong())
            ?: return ServerResponse.notFound().buildAndAwait()

        return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(
                userRepository.save(
                    receivedUser.toEntity().copy(id = user.id)
                ).toDto()
            )
    }

    suspend fun delete(req: ServerRequest): ServerResponse {
        val id = req.pathVariable("id")

        return if (userRepository.existsById(id.toLong())) {
            userRepository.deleteById(id.toLong())
            ServerResponse.noContent().buildAndAwait()
        } else {
            ServerResponse.notFound().buildAndAwait()
        }
    }
}