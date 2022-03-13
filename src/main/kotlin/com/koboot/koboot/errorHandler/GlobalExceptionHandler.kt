package com.koboot.koboot.errorHandler

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(CustomException::class)
    fun handleCustomException(e: CustomException): ResponseEntity<CustomExceptionResponse> {
        val response: CustomExceptionResponse = CustomExceptionResponse.builder()
            .errorCode(e.getErrorCode())
            .detail(e.getDetail())
            .build()

        return ResponseEntity(response, e.getStatusCode())
    }
}