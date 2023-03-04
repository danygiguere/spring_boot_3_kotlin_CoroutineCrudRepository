package com.example.reactive.dto

import com.example.reactive.entity.User

data class UserDto(
    val name: String,
    val email: String,
    val password: String
)

fun UserDto.toEntity(): User = User(
    name = name,
    email = email,
    password = password
)