package com.example.reactive.exception

class UnprocessableEntityException(val errors: Map<String, MutableMap<String, String?>>) : Throwable(message = null)
