package com.example.reactive.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.support.WebExchangeBindException
import java.util.function.Consumer

@ControllerAdvice
class ApplicationExceptionHandler {

//    @ExceptionHandler(WebExchangeBindException::class)
//    suspend fun handleWebExchangeBindException(e: WebExchangeBindException): ResponseEntity<Any> {
//        val errorMap: MutableMap<String, String?> = HashMap()
//        e.bindingResult.fieldErrors.forEach(Consumer { error: FieldError ->
//            errorMap[error.field] = error.defaultMessage
//        })
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//            .body(errorMap)
//    }

//    @ExceptionHandler(WebExchangeBindException::class)
//    suspend fun handleWebExchangeBindException(e: WebExchangeBindException): ResponseEntity<Any> {
//        val errorMap: MutableMap<String, String?> = HashMap()
//        e.bindingResult.fieldErrors.forEach(Consumer { error: FieldError ->
//            errorMap[error.field] = error.defaultMessage
//        })
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//            .body(errorMap)
//    }

//    @ExceptionHandler(WebExchangeBindException::class)
//    suspend fun handleWebExchangeBindException(e: WebExchangeBindException): ResponseEntity<Any> {
//        val errorMap: MutableMap<String, ArrayList<String>> = HashMap()
//        val stringArray: ArrayList<String> = ArrayList()
//        e.bindingResult.fieldErrors.forEach(Consumer { error: FieldError ->
//            error.defaultMessage?.let { stringArray.add(it) }
//            errorMap[error.field] = stringArray
//        })
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//            .body(errorMap)
//    }

    @ExceptionHandler(WebExchangeBindException::class)
    suspend fun handleWebExchangeBindException(e: WebExchangeBindException): ResponseEntity<Any> {
        val errorMap: MutableMap<String, ArrayList<String>> = HashMap()
        val stringArray: ArrayList<String> = ArrayList()
        e.bindingResult.fieldErrors.forEach(Consumer { error: FieldError ->
            error.defaultMessage?.let { stringArray.add(it) }
            errorMap[error.field] = stringArray
        })
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(errorMap)
    }



}