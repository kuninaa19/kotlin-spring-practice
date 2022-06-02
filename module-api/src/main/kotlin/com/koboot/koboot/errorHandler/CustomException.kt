package com.koboot.koboot.errorHandler

import org.springframework.http.HttpStatus

open class CustomException(private val statusCode: HttpStatus, private val errorCode: String, private val detail: String) :
    RuntimeException() {
    fun getStatusCode(): HttpStatus {
        return this.statusCode
    }

    fun getErrorCode(): String {
        return this.errorCode
    }

    fun getDetail(): String {
        return this.detail
    }
}