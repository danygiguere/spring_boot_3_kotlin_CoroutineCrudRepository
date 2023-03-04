package com.example.reactive.entity
import com.example.reactive.dto.UserDto
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
data class User(
    @Id var id: Long? = null,
    val name: String,
    val email: String,
    val password: String
)

fun User.toDto(): UserDto = UserDto(
    name = name,
    email = email,
    password = password
)