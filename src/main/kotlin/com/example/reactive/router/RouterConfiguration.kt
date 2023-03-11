package com.example.reactive.router

import com.example.reactive.handler.PostHandler
import com.example.reactive.handler.UserHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class RouterConfiguration(
    private val userHandler: UserHandler,
    private val postHandler: PostHandler) {

    @Bean
    fun apiRouter() = coRouter {
        accept(APPLICATION_JSON).nest {
            "/api".nest {
                GET("/users", userHandler::getAll)
                POST("/users", userHandler::add)
                GET("/users/{id}", userHandler::getById)
                PUT("/users/{id}", userHandler::update)
                DELETE("/users/{id}", userHandler::delete)

                GET("/posts", postHandler::getAll)
                POST("/posts", postHandler::add)
                GET("/posts/{id}", postHandler::getById)
                PUT("/posts/{id}", postHandler::update)
                DELETE("/posts/{id}", postHandler::delete)
            }
        }
    }
}

